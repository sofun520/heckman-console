'use strict';

/**
 * Config for the router
 */
angular.module('app')
    .run(
        ['$rootScope', '$state', '$stateParams',
            function ($rootScope, $state, $stateParams) {
                $rootScope.$state = $state;
                $rootScope.$stateParams = $stateParams;
            }
        ]
    )
    .config(
        ['$stateProvider', '$urlRouterProvider',
            function ($stateProvider, $urlRouterProvider) {

                $urlRouterProvider
                    .otherwise('/access/signin');
                $stateProvider
                    .state('app', {
                        abstract: true,
                        url: '/app',
                        templateUrl: 'admin/tpl/app.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load(['admin/controllers/app.js']);
                                }]
                        }
                    })
                    .state('app.permission', {
                        url: '/permission',
                        templateUrl: 'admin/tpl/permission.html',
                        resolve: {
                            deps: ['uiLoad',
                                function (uiLoad) {
                                    return uiLoad.load(['admin/controllers/permission.js']);
                                }]
                        }
                    })
                    .state('app.role', {
                        url: '/role',
                        templateUrl: 'admin/tpl/role.html',
                        resolve: {
                            deps: ['uiLoad',
                                function (uiLoad) {
                                    return uiLoad.load('admin/controllers/role.js');
                                }]
                        }
                    })
                    .state('app.user', {
                        url: '/user',
                        templateUrl: 'admin/tpl/user.html',
                        resolve: {
                            deps: ['uiLoad',
                                function (uiLoad) {
                                    return uiLoad.load('admin/controllers/user.js');
                                }]
                        }
                    })
                    .state('app.operateLog', {
                        url: '/operateLog',
                        templateUrl: 'admin/tpl/operateLog.html',
                        resolve: {
                            deps: ['uiLoad',
                                function (uiLoad) {
                                    return uiLoad.load('admin/controllers/operateLog.js');
                                }]
                        }
                    })
                    .state('app.smsTemp', {
                        url: '/smsTemp',
                        templateUrl: 'admin/tpl/smsTemp.html',
                        resolve: {
                            deps: ['uiLoad',
                                function (uiLoad) {
                                    return uiLoad.load('admin/controllers/smsTemp.js');
                                }]
                        }
                    })
                    .state('app.workFlow', {
                        url: '/workFlow',
                        templateUrl: 'admin/tpl/workFlow.html',
                        resolve: {
                            deps: ['uiLoad',
                                function (uiLoad) {
                                    return uiLoad.load('admin/controllers/workFlow.js');
                                }]
                        }
                    })
                    .state('access', {
                        url: '/access',
                        template: '<div ui-view class="fade-in-right-big smooth"></div>'
                    })
                    .state('access.signin', {
                        url: '/signin',
                        templateUrl: 'admin/tpl/signin.html',
                        resolve: {
                            deps: ['uiLoad',
                                function( uiLoad ){
                                    return uiLoad.load( ['admin/controllers/signin.js'] );
                                }]
                        }
                    })
            }
        ]
    );