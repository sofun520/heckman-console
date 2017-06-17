'use strict';

app.factory('frameService', function frameService($http) {
    return {
        getUserPermission: function getUserPermission(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/permission/getUserPermission",
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
        getUserMenus: function getUserMenus(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/permission/getUserMenus",
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
        logout: function logout(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/logout",
                data: query,
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
app.controller('frameController', ['$scope', 'frameService', 'commonUtil', function ($scope, frameService, commonUtil) {

    $scope.init = function () {
        $scope.goBackLogin();
        $scope.userinfo = {};
        $scope.getUserinfo();
        $scope.loadParentMenu();
        $scope.getUserPermission();

    }
    
    $scope.goBackLogin = function () {
        commonUtil.goBackLogin();
    }

    $scope.getUserinfo = function () {
        commonUtil.getUserInfo(function (error, data) {
            if (!error) {
                $scope.userinfo = data;
                console.log('================userinfo==================='+JSON.stringify($scope.userinfo));
            }
        });
    }

    $scope.getUserPermission = function () {
        var param = {};
        param.uUsername = $scope.userinfo.uUsername;
        frameService.getUserPermission(JSON.stringify(param), function (error, data) {
            //console.log(data);
        });
    }

    $scope.loadParentMenu = function () {
        var param = {};
        param.uUsername = $scope.userinfo.uUsername;
        param.pParent = 1;
        param.pType = 1;

        frameService.getUserMenus(JSON.stringify(param), function (error, data) {
            if (!error) {
                console.log('===============================>' + JSON.stringify(data));
                if (data.data.length > 0) {
                    $scope.menus = data.data;
                    $scope.loadSubMenu(data.data[0].pId);
                }
            }
        });
    }

    $scope.loadSubMenu = function (parentId) {
        var param = {};
        param.uUsername = $scope.userinfo.uUsername;
        param.pParent = parentId;
        param.pType = 1;

        frameService.getUserMenus(JSON.stringify(param), function (error, data) {
            if (!error) {
                //console.log(data);
                $scope.submenus = data.data;
                //console.log(data.data[0].pUrl);
            }
        });
    };

    $scope.logout = function () {
        frameService.logout(JSON.stringify({}), function (error, data) {
            if (!error && data.code == 0) {
                commonUtil.delUserInfo();
                window.location.href = '#';
            }
        });
    }


    $scope.tata = function () {
        alert('sdf');
    }


}]);