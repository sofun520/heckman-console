'use strict';

/* Controllers */
app.factory('operateLogService', function myService($http) {
    return {
        loadOLList: function loadOLList(query, callback) {
            $http({
                method: "post",
                url: config.urlConfig.rootUrl + "api/operateLog/query",
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
        exportList: function exportList(param, callback) {
            $http({
                url: config.urlConfig.rootUrl + 'api/operateLog/exportExcel',
                method: 'post',
                data: param,
                headers: {
                    'Content-type': 'application/json'
                },
                responseType: 'arraybuffer'
            }).success(function (data) {
                callback(null, data);
            }).error(function (e) {
                callback(e);
            });
        }
    }
});
app
    .controller('operateLogCtrl', ['$scope', 'operateLogService', '$compile', function ($scope, operateLogService, $compile) {

        $scope.init = function () {
            $scope.param = {};
            $scope.pageBean = {};
            $scope.pageBean.page = 1;
            $scope.pageBean.pageSize = 12;

            $scope.loadUserList();
        }

        $scope.loadUserList = function () {
            var obj = {};
            obj.pageBean = $scope.pageBean;
            obj.param = $scope.param;
            operateLogService.loadOLList(JSON.stringify(obj), function (error, data) {
                if (!error) {
                    console.log(data);
                    if (data.code == 0) {
                        if (data.data.length > 0) {
                            $scope.dataShow = true;
                            $scope.noDataShow = false;
                            $scope.olList = data.data;
                            if (data.page > 0 && data.pageCount > 0) {
                                $scope.page = data.page;
                                $scope.pageCount = data.pageCount;

                                var html = '';
                                for (var i = 0; i < data.pageCount; i++) {
                                    html += '<li><a href="javascript:void(0)" ng-click="changePage(' + (i + 1) + ')">' + (i + 1) + '</a></li>';
                                }
                                $("#pageHtml").html($compile(html)($scope));

                            }
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
            $scope.loadUserList();
        }

        $scope.searchList = function () {
            $scope.loadUserList();
        }


        $scope.exportFile = function () {
            var obj = {};
            //obj.pageBean = $scope.pageBean;
            obj.param = $scope.param;

            operateLogService.exportList(JSON.stringify(obj), function (error, data) {
                if (!error) {
                    var blob = new Blob([data], {type: "application/vnd.ms-excel"});
                    var filename = 'test' + '.xls';
                    if (window.navigator.msSaveOrOpenBlob) {
                        navigator.msSaveBlob(blob, filename);
                    } else {
                        var objectUrl = URL.createObjectURL(blob);
                        var a = document.createElement('a');
                        document.body.appendChild(a);
                        a.setAttribute('style', 'display:none');
                        a.setAttribute('href', objectUrl);
                        a.setAttribute('download', filename);
                        a.click();
                        URL.revokeObjectURL(objectUrl);
                    }
                }
            });
        }


    }]);