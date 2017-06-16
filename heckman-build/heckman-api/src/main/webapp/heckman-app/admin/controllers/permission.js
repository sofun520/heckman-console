'use strict';

/* Controllers */
app.factory('permissionService', function permissionService($http) {
    return {
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
        findPermission: function findPermission(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/permission/find",
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
        savePermisson: function savePermisson(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/permission/save",
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
        deletePermission: function deletePermission(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/permission/delete",
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
        permissionSetList: function permissionSetList(callback) {
            $http({
                method: "get",
                url: config.urlConfig.rootUrl + "api/permission/permissionSetList",
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
    .controller('permissionCtrl', ['$scope', 'permissionService', '$compile', 'commonUtil', function ($scope, permissionService, $compile, commonUtil) {

        $scope.init = function () {
            $scope.goBackLogin();
            $scope.addOrEdit = true;

            $scope.permissionSetList();

        }

        $scope.goBackLogin = function () {
            commonUtil.goBackLogin();
            commonUtil.getUserPermission(function (error, data) {
                if (!error && data.code == 0) {
                    $scope.permissions = {};
                    $.each(data.data, function (i, v) {
                        $scope.permissions[v.pToken] = true;
                    });
                }
            });
        }


        $scope.permissionSetList = function () {
            permissionService.permissionSetList(function (error, data) {
                console.log(JSON.stringify(data));
                if (!error) {
                    var aArray = data.data;

                    var fLevel = '';
                    var sLevel = '';
                    var tLevel = '';
                    var html = '';

                    //遍历一级
                    $.each(aArray, function (i, x) {
                        var bArray = x.cList;
                        fLevel = '';
                        //遍历二级

                        if (bArray.length < 1) {
                            html += '<tr><td rowspan="1"><a ng-click="test(' + x.pId + ')">' + x.pDescription + '</a></td><td></td><td></td></tr>';
                        } else {
                            $.each(bArray, function (i, y) {
                                var cArray = y.cList;

                                html += '<tr>';
                                //第一个元素
                                if (i == 0) {
                                    var bArray_length = 1;
                                    if (bArray.length == 0) {
                                        bArray_length = 1;
                                    } else {
                                        bArray_length = bArray.length;
                                    }
                                    html += '<td rowspan="' + bArray_length + '"><a ng-click="test(\'' + x.pId + '\')">' + x.pDescription + '</a></td>';
                                    html += '<td><a href="javascript:void(0)" ng-click="test(' + y.pId + ')"><i class="fa fa-fw fa-angle-right text-muted m-r-xs"></i>' + y.pDescription + '</a><br/></td>';
                                } else {
                                    html += '<td><a href="javascript:void(0)" ng-click="test(' + y.pId + ')"><i class="fa fa-fw fa-angle-right text-muted m-r-xs"></i>' + y.pDescription + '</a><br/></td>';
                                }

                                //tLevel+='<td>';
                                //遍历三级
                                html += '<td>';
                                $.each(cArray, function (i, z) {
                                    html += '<a href="javascript:void(0)" ng-click="test(' + z.pId + ')"><i class="fa fa-fw fa-angle-right text-muted m-r-xs"></i>' + z.pDescription + '</a><br/>';
                                });
                                html += '</td>';
                                //tLevel+='</td>';

                                html += '</tr>';

                            });
                        }

                    });
                    console.log(html);
                    $("#permissionList").html($compile(html)($scope));

                }
            });
        };


        $scope.test = function (id, pId) {
            $scope.addOrEdit = false;
            $scope.pParent = id;
            var param = JSON.stringify({"pId": id});
            permissionService.findPermission(param, function (error, data) {
                if (!error) {
                    console.log(data);
                    if (data.code == 0) {
                        $scope.per = data.data;
                    } else {
                        $.alert({title: '系统提示', content: data.msg});
                    }
                }
            });

        }


        $scope.submit = function () {
            var param = JSON.stringify($scope.per);
            permissionService.savePermisson(param, function (error, data) {
                if (!error) {
                    console.log(data);
                    if (data.code == 0) {
                        $.alert(data.msg);
                        //window.location.reload();
                        $scope.permissionSetList();
                        $scope.per = {};
                    } else {
                        $.alert({title: '系统提示', content: data.msg});
                    }
                }
            });
        }


        $scope.addSubLevel = function () {

            if (typeof($scope.per) == "undefined") {
                $.alert({title: '系统提示', content: '请选择一级菜单'});
            } else {

                var pId = $scope.per.pId;
                $scope.per = {};
                $scope.per.pParent = pId;

            }
        }

        $scope.deleteLevel = function () {
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
                            if (typeof($scope.per) == "undefined") {
                                $.alert({title: '系统提示', content: '请选择一级菜单'});
                            } else {
                                var param = $scope.per;
                                permissionService.deletePermission(JSON.stringify(param), function (error, data) {
                                    if (!error) {
                                        if (data.code == 0) {
                                            $scope.per = {};
                                            $scope.permissionSetList();
                                        }
                                    }
                                })
                            }
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

        $scope.resetOpt = function () {
            $scope.per = {};
            $scope.per.pParent = 1;
        }


    }]);