'use strict';

/* Controllers */
app.factory('smsService', function smsService($http) {
    return {
        loadList: function loadList(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/tSmsTemp/query",
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
        saveSmsTemp: function saveSmsTemp(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/tSmsTemp/save",
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
        delSmsTemp: function delSmsTemp(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/tSmsTemp/delete",
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
app
    .controller('smsTempCtrl', ['$scope', 'smsService', '$compile', 'commonUtil', function ($scope, smsService, $compile, commonUtil) {

        $scope.init = function () {

            $scope.input_form_show = false;

            $scope.smsTempForm = {};
            $scope.param = {};
            $scope.pageBean = {};

            $scope.pageBean.page = 1;
            $scope.pageBean.pageSize = 12;

            $scope.loadList();

            $scope.goBackLogin();

            $scope.getUserInfo();
        }

        $scope.getUserInfo = function () {
            commonUtil.getUserInfo(function (error, data) {
                $scope.userInfo = data;
            });
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

        $scope.loadList = function () {
            var obj = {};
            obj.pageBean = $scope.pageBean;
            obj.param = $scope.param;
            smsService.loadList(JSON.stringify(obj), function (error, data) {
                if (!error && data.code == 0) {
                    if (data.data.length > 0) {
                        $scope.dataShow = true;
                        $scope.noDataShow = false;
                        $scope.stList = data.data;

                        var html = '';
                        for (var i = 0; i < data.pageCount; i++) {
                            html += '<li><a href="" ng-click="changePage(' + (i + 1) + ')">' + (i + 1) + '</a></li>';
                        }
                        $("#foot_page").html($compile(html)($scope));

                        console.log(JSON.stringify(data));
                    } else {
                        $scope.dataShow = false;
                        $scope.noDataShow = true;
                    }

                }
            });
        }

        $scope.changePage = function (page) {
            $scope.pageBean.page = page;
            $scope.loadList();
        }


        $scope.search = function () {
            $scope.pageBean.page = 1;
            $scope.loadList();
        }

        $scope.clear = function () {
            $scope.param = {};
        }

        $scope.addNew = function () {
            //var html = $compile($("#input_form").html())($scope);
            var html = $("#input_form").html();
            $.confirm({
                title: '新增模板信息',
                content: html,
                type: 'blue',
                typeAnimated: true,
                columnClass: 'col-md-6 col-md-offset-3',
                buttons: {
                    tryAgain: {
                        text: '提交',
                        btnClass: 'btn-blue',
                        action: function () {
                            alert($("#tName").val());
                            var param = {};
                            param.smsTemp = $scope.smsTempForm;
                            param.user = $scope.userInfo;
                            /*smsService.saveSmsTemp(JSON.stringify(param), function (error, data) {
                             if (!error && data.code == 0) {
                             $.alert({title: '系统提示', content: '添加成功'});
                             }
                             });*/
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

        $scope.del = function (id) {
            $.confirm({
                title: '系统提示',
                content: '是否删除？',
                type: 'blue',
                //typeAnimated: true,
                //columnClass: 'col-md-6 col-md-offset-3',
                buttons: {
                    tryAgain: {
                        text: '提交',
                        btnClass: 'btn-blue',
                        action: function () {
                            var param = {"tId": id};
                            smsService.delSmsTemp(JSON.stringify(param), function (error, data) {
                                if (!error && data.code == 0) {
                                    $.alert({title: '系统提示', content: '删除成功'});
                                    $scope.loadList();
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
    }]);