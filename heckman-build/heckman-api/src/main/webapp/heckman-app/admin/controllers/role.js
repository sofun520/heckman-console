'use strict';

/* Controllers */
app.factory('roleService', function myService($http) {
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
        loadRoleList: function loadRoleList(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/role/query",
                data: query,
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }).success(function (data) {
                console.log(JSON.stringify(data));
                callback(null, data);
            }).error(function (e) {
                callback(e);
            });
        },
        deleteRole: function deleteRole(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/role/delete",
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
                url: config.urlConfig.rootUrl + "api/role/saveRolePermission",
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
        saveRole: function saveRole(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/role/save",
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
        findRole: function findRole(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/role/find",
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
    .controller('roleCtrl', ['roleService', '$scope', '$compile', 'commonUtil', function (roleService, $scope, $compile, commonUtil) {
        $scope.init = function () {
            $scope.param = {};
            $scope.pageBean = {};
            $scope.pageBean.page = 1;
            $scope.pageBean.pageSize = 6;


            $scope.goBackLogin();
            $scope.roleId = 0;
            $scope.loadRoleList();
            $scope.assignMenuShow = false;
            $scope.roleInfoShow = true;
            $scope.addOrEdit = true;
            $scope.roleInfo = {};

            //$scope.loadRolePermissionList();
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

        $scope.loadRoleList = function () {
            var obj = {};
            obj.pageBean = $scope.pageBean;
            obj.param = $scope.param;
            roleService.loadRoleList(JSON.stringify(obj), function (error, data) {
                if (!error) {
                    console.log(data);
                    if (data.code == 0) {
                        if (data.data.length > 0) {
                            $scope.dataShow = true;
                            $scope.noDataShow = false;
                            $scope.roleList = data.data;

                            var html = '';
                            for (var i = 0; i < data.pageCount; i++) {
                                html += '<li><a href="javascript:void(0)" ng-click="changePage(' + (i + 1) + ')">' + (i + 1) + '</a></li>';
                            }
                            $("#pageHtml").html($compile(html)($scope));

                        } else {
                            $scope.dataShow = false;
                            $scope.noDataShow = true;
                        }
                    } else {
                        $.alert({title: '系统提示', content: data.msg});
                    }
                }
            });
        }

        $scope.changePage = function (page) {
            $scope.pageBean.page = page;
            $scope.loadRoleList();
        }

        $scope.delRole = function (pId) {
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


        $scope.addRole = function (id) {
            $scope.assignMenuShow = false;
            $scope.roleInfoShow = true;
            $scope.addOrEdit = true;
        }

        $scope.editRole = function (id) {
            $scope.addOrEdit = false;
            $scope.assignMenuShow = false;
            $scope.roleInfoShow = true;
            var param = JSON.stringify({"rId": id});
            roleService.findRole(param, function (error, data) {
                if (!error) {
                    console.log(data);
                    if (data.code == 0) {
                        $scope.roleInfo = data.data;
                    } else {
                        $.alert({title: '系统提示', content: data.msg});
                    }
                }
            });
        }

        $scope.delRole = function (id) {
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
                            var param = JSON.stringify({"rId": id});
                            roleService.deleteRole(param, function (error, data) {
                                if (!error && data.code == 0) {
                                    //window.location.reload();
                                    $scope.loadRoleList();

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


        $scope.reset = function () {
            $scope.rDescription = '';
        }

        //var treeObj;
        $scope.assignMenu = function (id) {
            $scope.assignMenuShow = true;
            $scope.roleInfoShow = false;

            $scope.roleId = id;

            $scope.permissionSetList();
            $scope.loadRolePermissionList(id);

        }


        $scope.save = function () {
            if ($scope.roleId != 0) {
                var menuId = '';
                var nodes = treeObj.getCheckedNodes(true);
                for (var i = 0; i < nodes.length; i++) {
                    if (i == nodes.length - 1) {
                        menuId += nodes[i].id;
                    } else {
                        menuId += nodes[i].id + ',';
                    }
                }

                var param = JSON.stringify({"pDescription": menuId, "pId": $scope.roleId});
                roleService.saveRolePermission(param, function (error, data) {
                    if (!error) {
                        console.log(data);
                        if (data.code == 0) {
                            $scope.assignMenu($scope.roleId);

                            //window.location.reload();
                        } else {
                            $.alert({title: '系统提示', content: data.msg});
                        }
                    }
                });
            } else {
                $.alert('请为具体角色勾选权限菜单！');
            }
        }

        $scope.submit = function () {
            var param = JSON.stringify($scope.roleInfo);
            roleService.saveRole(param, function (error, data) {
                if (!error) {
                    if (data.code == 0) {
                        //window.location.reload();
                        $scope.loadRoleList();
                    }
                }
            });

        }


        $scope.permissionSetList = function () {
            roleService.permissionSetList(function (error, data) {
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
                                    html += '<td rowspan="' + bArray_length + '"><input type="checkbox" ng-model="checkboxs.a' + x.pId + '" ng-click="chk(' + x.pId + ')"><a ng-click="test(\'' + x.pId + '\')">' + x.pDescription + '</a></td>';
                                    html += '<td><a href="javascript:void(0)" ng-click="test(' + y.pId + ')"><input type="checkbox" ng-model="checkboxs.a' + y.pId + '" ng-click="chk(' + y.pId + ')">' + y.pDescription + '</a><br/></td>';
                                } else {
                                    html += '<td><a href="javascript:void(0)" ng-click="test(' + y.pId + ')"><input type="checkbox" ng-model="checkboxs.a' + y.pId + '" ng-click="chk(' + y.pId + ')">' + y.pDescription + '</a><br/></td>';
                                }

                                //tLevel+='<td>';
                                //遍历三级
                                html += '<td>';
                                $.each(cArray, function (i, z) {
                                    html += '<a href="javascript:void(0)" ng-click="test(' + z.pId + ')"><input type="checkbox" ng-model="checkboxs.a' + z.pId + '" ng-click="chk(' + z.pId + ')">' + z.pDescription + '</a><br/>';
                                });
                                html += '</td>';
                                //tLevel+='</td>';

                                html += '</tr>';

                            });
                        }

                    });
                    console.log(html);
                    $("#permissionList").html($compile(html)($scope));

                    //var aaaa='{"a1":true,"a2":true,"a3":true,"a4":true}';
                    //$scope.checkboxs = eval("("+aaaa+")");
                    //$scope.checkboxs.a2 = true;
                    //$scope.checkboxs.a3 = true;

                }
            });
        };


        $scope.loadRolePermissionList = function (id) {
            var query = {"rId": id};
            roleService.rolePermissionTree(JSON.stringify(query), function (error, data) {
                if (!error) {
                    console.log(JSON.stringify(data));
                    $scope.rolePermissionList = data.data;
                    var data_str = '{';
                    $.each(data.data, function (i, v) {
                        if (i == (data.data.length - 1)) {
                            data_str += '\"a' + v.id + '\":' + v.checked;
                        } else {
                            data_str += '\"a' + v.id + '\":' + v.checked + ',';
                        }
                    });
                    data_str += '}';
                    console.log(data_str);
                    $scope.checkboxs = eval("(" + data_str + ")");
                }
            })
        }


        $scope.chk = function (id) {
            //alert(JSON.stringify($scope.checkboxs));
            var isChecked;
            $.each($scope.rolePermissionList, function (i, v) {
                if (v.id == id) {
                    isChecked = v.checked;
                    return false;
                }
            })
            /*if (isChecked == 'true') {
             alert('true===========>');
             } else if (isChecked == 'false') {
             alert('false===========>');
             }*/
            var param = {roleId: $scope.roleId, permissionId: id};
            $scope.saveRlePermission(param);
        }

        $scope.saveRlePermission = function (param) {
            roleService.saveRolePermission(JSON.stringify(param), function (error, data) {
                if (!error) {
                    if (data.code == 0) {
                        $scope.permissionSetList();
                        $scope.loadRolePermissionList($scope.roleId);
                        //$.alert({title: '系统提示', content: '操作成功!'});
                    } else {
                        $.alert({title: '系统提示', content: data.msg});
                    }
                }
            });
        }

        $scope.addRole = function () {
            $scope.roleInfo = {};
        }

        $scope.test = 'abcd2323';

    }]);