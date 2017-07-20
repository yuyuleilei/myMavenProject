/**
 * Created by Administrator on 04/04/2016.
 */
var config = {
    apiPrefix : '/mgt'
};

String.prototype.format = function(){
    var args = arguments;
    return this.replace(/\{(\d+)\}/g,function(m,i){ return args[i];});
};

var tool = {};
tool.getParameter = function (name) {
    var r = new RegExp("(\\?|#|&)" + name + "=([^&#]*)(&|#|$)");
    var m = location.href.match(r);
    return (!m ? "" : m[2]);
};
tool.isMobile = function(mobile){
    return /^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$/.test(mobile);
};
tool.isQQ = function(qq){
    return /^[1-9][0-9]{4,9}$/.test(qq);
};
tool.isNum = function(num){
    return /^\d+(\.\d{1,2})?$/.test(num);
};
tool.isEmail = function(email){
    return /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(email);
};
tool.post = function(ngHttp,api,data,callback) {
    ngHttp({
        url: config.apiPrefix + "/api" + api,
        data: data,
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        method: 'POST'
    }).success(function (data, header, config, status) {
        if (data.errorCode === 0) {
            callback(data.content);
        } else {
            alert(data.description);
            if (console){
                console.log(error[data.errorCode]);
            }
        }
    }).error(function (data, header, config, status) {
        alert(data.description);
    });
};
tool.get = function(ngHttp,api,callback) {
    ngHttp.get(config.apiPrefix+'/api'+api,{ cache: true })
        .success(function(data) {
            if (data.errorCode == 0) {
                callback(data.content);
            } else {
                alert(data.description);
                if (console){
                    console.log(error[data.errorCode]);
                }
            }
        })
        .error(function(data) {
            alert(data.description);
        });
};
tool.getByParam = function(ngHttp,api,dataUrlJson,callback) {

    var urlData = "?";
    for(var name in dataUrlJson){
        urlData += name + "=" + dataUrlJson[name];
    }

    tool.get(ngHttp,api+urlData,callback);

};
tool.setCookie = function (ngCookies,cookieKey,cookieValue,timeout) {
    var expireDate = new Date();
    expireDate.setTime(expireDate.getTime() + timeout);
    ngCookies.put(cookieKey,JSON.stringify(cookieValue),{"expires":expireDate});

};
tool.getCookie = function (ngCookies,cookieKey) {
    if (ngCookies.get(cookieKey)){
        return JSON.parse(ngCookies.get(cookieKey));
    }
    return ngCookies.get(cookieKey);
};
var error = {
    "0":"成功",
    "1":"警告",
    "2":"未知异常",
    "3":"系统内部错误",
    "4":"缓存异常",
    "5":"非法token",
    "6":"参数不正确",
    "7":"非法IP地址",
    "8":"会话超时",
    "9":"访问超过限制",
    "10":"没有结果",
    "11":"没有权限",
    "12":"加密异常",
    "13":"解密异常",
    "8001":"文件不能为空",
    "8002":"创建账户失败",
    "8003":"公司已存在",
    "8004":"公司帐户不存在",
    "8005":"创建帐户失败",
    "8006":"CRM数据异常"
};
