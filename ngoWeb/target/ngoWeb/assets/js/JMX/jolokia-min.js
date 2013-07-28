

(function(){var _jolokiaConstructorFunc=function($){var DEFAULT_CLIENT_PARAMS={type:"POST",jsonp:false};var GET_AJAX_PARAMS={type:"GET"};var POST_AJAX_PARAMS={type:"POST",processData:false,dataType:"json",contentType:"text/json"};var PROCESSING_PARAMS=["maxDepth","maxCollectionSize","maxObjects","ignoreErrors"];function Jolokia(param){if(!(this instanceof arguments.callee)){return new Jolokia(param);}
this.CLIENT_VERSION="1.0.5";var jobs=[];var pollerIsRunning=false;if(typeof param==="string"){param={url:param};}
$.extend(this,DEFAULT_CLIENT_PARAMS,param);this.request=function(request,params){var opts=$.extend({},this,params);assertNotNull(opts.url,"No URL given");var ajaxParams={};$.each(["username","password","timeout"],function(i,key){if(opts[key]){ajaxParams[key]=opts[key];}});if(extractMethod(request,opts)==="post"){$.extend(ajaxParams,POST_AJAX_PARAMS);ajaxParams.data=JSON.stringify(request);ajaxParams.url=ensureTrailingSlash(opts.url);}else{$.extend(ajaxParams,GET_AJAX_PARAMS);ajaxParams.dataType=opts.jsonp?"jsonp":"json";ajaxParams.url=opts.url+"/"+constructGetUrlPath(request);}
ajaxParams.url=addProcessingParameters(ajaxParams.url,opts);if(opts.ajaxError){ajaxParams.error=opts.ajaxError;}
if(opts.success){var success_callback=constructCallbackDispatcher(opts.success);var error_callback=constructCallbackDispatcher(opts.error);ajaxParams.success=function(data){var responses=$.isArray(data)?data:[data];for(var idx=0;idx<responses.length;idx++){var resp=responses[idx];if(Jolokia.isError(resp)){error_callback(resp,idx);}else{success_callback(resp,idx);}}};$.ajax(ajaxParams);return null;}else{if(opts.jsonp){throw Error("JSONP is not supported for synchronous requests");}
ajaxParams.async=false;var xhr=$.ajax(ajaxParams);if(httpSuccess(xhr)){return $.parseJSON(xhr.responseText);}else{return null;}}};this.register=function(){if(arguments.length<2){throw"At a least one request must be provided";}
var callback=arguments[0],requests=Array.prototype.slice.call(arguments,1),job;if(typeof callback==='object'){job={success:callback.success,error:callback.error,callback:null};}else if(typeof callback==='function'){job={success:null,error:null,callback:callback};}else{throw"First argument must be either a callback func "+"or an object with 'success' and 'error' attrs";}
job.requests=requests;jobs.push(job);return jobs.length-1;};this.unregister=function(handle){if(handle<jobs.length){jobs.splice(handle,1);}};this.start=function(interval){interval=interval||this.fetchInterval||30000;if(pollerIsRunning){if(interval===this.fetchInterval){return;}
this.stop();}
this.fetchInterval=interval;this.timerId=setInterval(callJolokia(this,jobs),interval);pollerIsRunning=true;};this.stop=function(){if(!pollerIsRunning&&this.timerId!=undefined){return;}
clearInterval(this.timerId);this.timerId=null;pollerIsRunning=false;};this.isRunning=function(){return pollerIsRunning;};}
function callJolokia(jolokia,jobs){return function(){var errorCbs=[],successCbs=[],reqs,i,j,len=jobs.length;var requests=[];var opts;for(i=0;i<len;i++){var job=jobs[i];reqs=job!=null?job.requests:void 0;var reqsLen=reqs.length;if(job.success){var successCb=cbSuccessErrorClosure("success",job,i);var errorCb=cbSuccessErrorClosure("error",job,i);for(j=0;j<reqsLen;j++){requests.push(reqs[j]);successCbs.push(successCb);errorCbs.push(errorCb);}}else{var dCb=cbCallbackClosure(job,jolokia);for(j=0;j<reqsLen-1;j++){requests.push(reqs[j]);successCbs.push(dCb.cb);errorCbs.push(dCb.cb);}
requests.push(reqs[reqsLen-1]);successCbs.push(dCb.lcb);errorCbs.push(dCb.lcb);}}
opts={success:function(resp,j){return successCbs[j].apply(jolokia,[resp,j]);},error:function(resp,j){return errorCbs[j].apply(jolokia,[resp,j]);}};return jolokia.request(requests,opts);};}
function cbCallbackClosure(job,jolokia){var responses=[],callback=job.callback;return{cb:function(resp,j){responses.push(resp);},lcb:function(resp,j){responses.push(resp);callback.apply(jolokia,responses);}};}
function cbSuccessErrorClosure(type,job,i){return function(resp,j){if(job[type]){job[type](resp,i,j)}}}
function constructCallbackDispatcher(callback){if(callback==null){return function(response){console.log("Ignoring response "+JSON.stringify(response));};}else if(callback==="ignore"){return function(){};}
var callbackArray=$.isArray(callback)?callback:[callback];return function(response,idx){callbackArray[idx%callbackArray.length](response,idx);}}
function extractMethod(request,opts){var methodGiven=opts&&opts.method?opts.method.toLowerCase():null,method;if(methodGiven){if(methodGiven==="get"){if($.isArray(request)){throw new Error("Cannot use GET with bulk requests");}
if(request.type.toLowerCase()==="read"&&$.isArray(request.attribute)){throw new Error("Cannot use GET for read with multiple attributes");}
if(request.target){throw new Error("Cannot use GET request with proxy mode");}}
method=methodGiven;}else{method=$.isArray(request)||(request.type.toLowerCase()==="read"&&$.isArray(request.attribute))||request.target?"post":"get";}
if(opts.jsonp&&method==="post"){throw new Error("Can not use JSONP with POST requests");}
return method;}
function addProcessingParameters(url,opts){var sep=url.indexOf("?")>0?"&":"?";$.each(PROCESSING_PARAMS,function(i,key){if(opts[key]!=null){url+=sep+key+"="+opts[key];sep="&";}});return url;}
function constructGetUrlPath(request){var type=request.type;assertNotNull(type,"No request type given for building a GET request");type=type.toLowerCase();var extractor=GET_URL_EXTRACTORS[type];assertNotNull(extractor,"Unknown request type "+type);var result=extractor(request);var parts=result.parts||{};var url=type;$.each(parts,function(i,v){url+="/"+Jolokia.escape(v)});if(result.path){url+=(result.path[0]=='/'?"":"/")+result.path;}
return url;}
function ensureTrailingSlash(url){return url.replace(/\/*$/,"/");}
var GET_URL_EXTRACTORS={"read":function(request){if(request.attribute==null){return{parts:[request.mbean]};}else{return{parts:[request.mbean,request.attribute],path:request.path};}},"write":function(request){return{parts:[request.mbean,request.attribute,valueToString(request.value)],path:request.path};},"exec":function(request){var ret=[request.mbean,request.operation];if(request.arguments&&request.arguments.length>0){$.each(request.arguments,function(index,value){ret.push(valueToString(value));});}
return{parts:ret};},"version":function(){return{};},"search":function(request){return{parts:[request.mbean]};},"list":function(request){return{path:request.path};}};function valueToString(value){if(value==null){return"[null]";}
if($.isArray(value)){var ret="";for(var i=0;i<value.length;i++){ret+=value==null?"[null]":singleValueToString(value[i]);if(i<value.length-1){ret+=",";}}
return ret;}else{return singleValueToString(value);}}
function singleValueToString(value){if(typeof value==="string"&&value.length==0){return"\"\"";}else{return value.toString();}}
function httpSuccess(xhr){try{return!xhr.status&&location.protocol==="file:"||xhr.status>=200&&xhr.status<300||xhr.status===304||xhr.status===1223;}catch(e){}
return false;}
function assertNotNull(object,message){if(object==null){throw new Error(message);}}
Jolokia.prototype.escape=Jolokia.escape=function(part){return part.replace(/!/g,"!!").replace(/\//g,"!/");};Jolokia.prototype.isError=Jolokia.isError=function(resp){return resp.status==null||resp.status!=200;};return Jolokia;};(function(root,factory){if(typeof define==='function'&&define.amd){define(["jquery"],factory);}else{root.Jolokia=factory(root.jQuery);}}(this,function(jQuery){return _jolokiaConstructorFunc(jQuery);}));}());