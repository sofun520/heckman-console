'use strict';

/* Controllers */
app.factory('loginService', function loginService($http) {
    return {
        loadUserInfo: function loadUserInfo(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/user/userInfo",
                data: query,
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }).success(function (data) {
                callback(null, data);
            }).error(function (e) {
                callback(e);
            });
        },
        checkLogin: function checkLogin(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/checkLogin",
                data: query,
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }).success(function (data) {
                callback(null, data);
            }).error(function (e) {
                callback(e);
            });
        },
        checkVerifyCode: function checkVerifyCode(query, callback) {
            $http({
                method: "get",
                url: config.urlConfig.rootUrl + "api/verifyCode/checkVerifyCode?verifyCode=" + query,
                //data : query,
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
app
    .controller('SigninFormController', ['$scope', 'loginService', '$translateCookieStorage', function ($scope, loginService, $translateCookieStorage) {

        $scope.app = {
            name: 'Heckman',
            version: '1.3.3',
        }

        $scope.login = function () {
            var param = {"uUsername": $scope.username, "uPassword": $scope.password};
            loginService.checkLogin(param, function (error, data) {
                if (!error) {
                    console.log(data);
                    if (data.code == 0) {
                        $translateCookieStorage.set('user', data.data);
                        console.log(data.data);
                        location.href = '#/app/permission';
                    } else {
                        $scope.submiting = false;
                        $scope.loginMsg = data.msg;
                    }
                }
            });
        }

    }]);