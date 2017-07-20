$(function () {
    var ajax = $.ajax;
    $.ajax = function (param) {
        if (param.url.indexOf(config.apiPrefix) == -1) {
            param.url = config.apiPrefix + "/api/" + param.url;
        }
        return ajax(param);
    };

    $.session = {
        put: function (key, value) {
            if (!key || !value) return;
            if (value instanceof Object) {
                sessionStorage.setItem(key + "_is_obj", 1);
                sessionStorage.setItem(key, JSON.stringify(value));
            } else {
                sessionStorage.setItem(key, value.toString());
            }
        },
        get: function (key) {
            if (!key) return null;
            var value = sessionStorage.getItem(key);
            if (sessionStorage.getItem(key + "_is_obj")) {
                value = eval("(" + value + ")");
            }
            return value;
        },
        remove: function (key) {
            if (!key) return;
            sessionStorage.removeItem(key);
            sessionStorage.removeItem(key + "_is_obj");
        }
    };
});