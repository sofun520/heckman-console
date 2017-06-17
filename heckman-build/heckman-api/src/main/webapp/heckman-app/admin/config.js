/**
 * Created by heckman on 2017/4/8.
 */

var config = {
    urlConfig: {
        rootUrl: 'http://localhost:8080/',
        localUrl: 'http://localhost:8080/heckman-app/',
        homeUrl: '#/app/permission'
    }
};

app.factory('commonUtil', function commonUtil($translateCookieStorage, $http, $rootScope) {
    return {
        getUserInfo: function getUserInfo(callback) {
            var data = $translateCookieStorage.get('user');
            callback(null, data);
        },
        goBackLogin: function goBackLogin() {
            var data = $translateCookieStorage.get('user');
            if (typeof(data) == "undefined" || data == null) {
                window.location.href = config.urlConfig.rootUrl;
            }
        },
        delUserInfo: function delUserInfo() {
            $translateCookieStorage.set('user', null);
        },
        getUserPermission: function getUserPermission(callback) {
            var data = $translateCookieStorage.get('user');
            var user = {};
            user.uUsername = data.uUsername;
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/permission/getUserPermission",
                data: JSON.stringify(user),
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }).success(function (data) {
                callback(null, data);
            }).error(function (e) {
                callback(e);
            });
        }
    }
});