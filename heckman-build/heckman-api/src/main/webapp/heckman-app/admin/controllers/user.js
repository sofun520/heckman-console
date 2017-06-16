'use strict';

/* Controllers */
app.factory('userService', function userService($http) {
    return {
        loadUserList: function loadUserList(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/user/query",
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
        deleteUser: function deleteRole(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/user/delete",
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
        rolePermissionTree: function rolePermissionTree(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/permission/rolePermissionTree",
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
        saveRolePermission: function saveRolePermission(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/permission/saveRolePermission",
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
        findUser: function findUser(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/user/find",
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
        saveUser: function saveUser(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/user/save",
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
        loadRoleList: function loadRoleList(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/role/query",
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
        userRoleTree: function userRoleTree(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/user/userRoleTree",
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
        saveUserRole: function saveUserRole(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/user/saveUserRole",
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
app.controller('userCtrl', ['$scope', 'userService', '$compile', function ($scope, userService, $compile) {

    $scope.init = function () {
        $scope.param = {};
        $scope.pageBean = {};
        $scope.pageBean.page = 1;
        $scope.pageBean.pageSize = 12;

        $scope.userShow = true;
        $scope.assignRoleShow = false;
        $scope.addOrEditFlag = true;
        $scope.loadUserList();
    }

    $scope.loadUserList = function () {
        var param = {}
        userService.loadUserList(JSON.stringify(param), function (error, data) {
            if (!error) {
                if (data.data.length > 0) {
                    $scope.dataShow = true;
                    $scope.noDataShow = false;
                    $scope.userList = data.data;
                } else {
                    $scope.dataShow = false;
                    $scope.noDataShow = true;
                }
            }
        });
    }

    $scope.delUser = function (uId) {
        $.confirm({
            title: '系统提示',
            content: '是否删除?',
            type: 'blue',
            typeAnimated: true,
            buttons: {
                tryAgain: {
                    text: '确定',
                    btnClass: 'btn-blue',
                    action: function () {
                        var user = {};
                        user.uId = uId;
                        userService.deleteUser(JSON.stringify(user), function (error, data) {
                            if (!error && data.code == 0) {
                                $scope.loadUserList();
                                $.alert({title: '系统提示', content: '删除成功!'});
                            } else {
                                $.alert({title: '系统提示', content: data});
                            }
                        });
                    }
                },
                cancle: {
                    text: '取消',
                    action: function () {
                    }
                }
            }
        });
    }

    $scope.editUser = function (uId) {
        $scope.userShow = true;
        $scope.assignRoleShow = false;
        $scope.addOrEditFlag = false;
        var user = {};
        user.uId = uId;
        userService.findUser(JSON.stringify(user), function (error, data) {
            if (!error && data.code == 0) {
                $scope.userInfo = data.data;
            } else {
                $.alert({title: '系统提示', content: data.msg});
            }
        })
    }

    $scope.submit = function () {
        userService.saveUser(JSON.stringify($scope.userInfo), function (error, data) {
            if (!error && data.code == 0) {
                $scope.userInfo = {};
                $scope.loadUserList();
                $.alert({title: '系统提示', content: '提交成功!'});
            } else {
                $.alert({title: '系统提示', content: data.msg});
            }
        })
    }

    $scope.addUser = function () {
        $scope.addOrEditFlag = true;
        $scope.userInfo = {};
    }

    $scope.assignRole = function (uId) {
        $scope.uId = uId;
        $scope.userShow = false;
        $scope.assignRoleShow = true;

        var obj = {}
        obj.param = {};
        obj.pageBean = $scope.pageBean;
        userService.loadRoleList(JSON.stringify(obj), function (error, data) {
            if (!error && data.code == 0) {
                var html = '';
                $.each(data.data, function (i, v) {
                    html += '<input type="checkbox" ng-model="checkboxs.a' + v.rId + '" ng-click="chose(' + v.rId + ')">' + v.rDescription + '</br>';
                });
                $("#roleList").html($compile(html)($scope));
            }
        });
        userService.userRoleTree(JSON.stringify({"uId": uId}), function (error, data) {
            var tt = '{';
            $.each(data.data, function (i, v) {
                if (i == data.data.length - 1) {
                    tt += '\"a' + v.id + '\":' + v.checked;
                } else {
                    tt += '\"a' + v.id + '\":' + v.checked + ',';
                }
            });
            tt += '}';
            $scope.checkboxs = eval("(" + tt + ")");
            console.log(tt);
        });
    }

    $scope.chose = function (roleId) {
        var param = {};
        param.userId = $scope.uId;
        param.roleId = roleId;
        userService.saveUserRole(JSON.stringify(param), function (error, data) {
            if (!error && data.code == 0) {
                //$.alert({title: '系统提示', content: '提交成功!'});
            }
        });
    }

}]);