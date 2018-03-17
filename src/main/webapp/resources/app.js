var app = angular.module('app', ['ngRoute']).config(
    ['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/total', {
            controller: 'totalController',
            templateUrl: 'total.html'
        }).when('/changePassWord', {
            controller: 'changePassWordController',
            templateUrl: 'changePassWord.html'
        }).when('/itemList', {
            controller: 'itemListController',
            templateUrl: 'itemList.html'
        }).when('/oneOrder', {
            controller: 'oneOrderController',
            templateUrl: 'oneOrder.html'
        }).when('/reCallback', {
            controller: 'reCallbackController',
            templateUrl: 'reCallback.html'
        }).otherwise('/total');
    }]);
app.controller('itemListController', function ($scope, $http,$filter) {
    var dateOption = {format: 'yyyy/m/d', autoclose: true, language: "zh-CN"}
    var date = new Date();
    var dataFormat = date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate();
    $('#begin_time1').datepicker(dateOption);
    $('#end_time1').datepicker(dateOption);
    $('#begin_time1').val(dataFormat);
    $('#end_time1').val(dataFormat);
    var startNow = null;
    var endNow = null;
    $("#table").bootstrapTable({
        striped: true,//是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortStable: true,
        checkbox: true,
        showPaginationSwitch: true,
        sortOrder: "asc",                   //排序方式
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 30,                       //每页的记录行数（*）
        pageList: [10, 30, 50, 100],        //可供选择的每页的行数（*）
        strictSearch: true,
        showColumns: true,
        clickToSelect: true,                //是否启用点击选中行
        showRefresh: true,  //显示刷新按钮
        search: true,//是否显示右上角的搜索框
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        columns: [{
            field: 'Number',
            title: '行号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {
            field: 'merId',
            title: '商户号'
        }, {
            field: 'pTitle',
            title: '商品描述'
        }, {
            field: 'merchantNo',
            title: '商户订单号'
        }, {
            field: 'tradeNo',
            title: '平台订单号'
        }, {
            field: 'wechatNo',
            title: '支付订单号'
        }, {
            field: 'pType',
            title: '支付类型',
            formatter: function (value) {
                switch (value) {
                    case 1:
                        return "微信APP";
                    case 2:
                        return "微信扫码";
                    case 3:
                        return "微信公众号";
                    case 4:
                        return "微信WAP";
                    case 5:
                        return "其他";
                    case 6:
                        return "支付宝APP";
                    case 7:
                        return "支付宝扫码";
                    case 8:
                        return "支付宝WAP";
                    default:
                        return "其他";
                }
            }
        }, {
            field: 'pFee',
            title: '交易金额'
        }, {
            field: 'pState',
            title: '支付状态',
            formatter: function (value) {
                if (value == 1) {
                    return '支付成功';
                } else {
                    return '支付失败';
                }
            }
        }, {
            field: 'createTime',
            title: '订单创建时间',
            formatter: function (value) {
                return $filter('date')(value,'yyyy-MM-dd HH:mm:ss');
            }
        }, {
            field: 'pTime',
            title: '订单交易时间',
            formatter: function (value) {
                return $filter('date')(value,'yyyy-MM-dd HH:mm:ss');
            }
        }]
    });
    $scope.select = function () {
        var startTime = $("#begin_time1").val();
        var endTime = $("#end_time1").val();
        var payType = $("#payType").val();
        if (startTime == startNow && endTime == endNow) {
        }
        startNow = $("#begin_time1").val();
        endNow = $("#end_time1").val();
        $http({
            url: "select/item",
            contentType: "application/x-www-form-urlencoded",
            method: 'POST',
            params: {
                startTime: startTime,
                endTime: endTime,
                payType: payType
            }
        }).success(function (ret) {
            if (ret.state) {
                $scope.totalData = ret;
                $scope.displayData(ret.data)
            } else {
                alert(ret.errorMsg)
            }
        }).error(function (data) {
        });
    }
    $scope.displayData = function (data) {
        $("#table").bootstrapTable("load", data)
    }
});
app.controller('oneOrderController', function ($scope, $http,$filter) {
    $("#table").bootstrapTable({
        striped: true,//是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortOrder: "asc",                   //排序方式
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        columns: [{
            field: 'Number',
            title: '行号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {
            field: 'merId',
            title: '商户号'
        }, {
            field: 'pTitle',
            title: '商品描述'
        }, {
            field: 'merchantNo',
            title: '商户订单号'
        }, {
            field: 'tradeNo',
            title: '平台订单号'
        }, {
            field: 'wechatNo',
            title: '支付订单号'
        }, {
            field: 'pType',
            title: '支付类型',
            formatter: function (value) {
                switch (value) {
                    case 1:
                        return "微信APP";
                    case 2:
                        return "微信扫码";
                    case 3:
                        return "微信公众号";
                    case 4:
                        return "微信WAP";
                    case 5:
                        return "其他";
                    case 6:
                        return "支付宝APP";
                    case 7:
                        return "支付宝扫码";
                    case 8:
                        return "支付宝WAP";
                    default:
                        return "其他";
                }
            }
        }, {
            field: 'pFee',
            title: '交易金额'
        }, {
            field: 'pState',
            title: '支付状态',
            formatter: function (value) {
                if (value == 1) {
                    return '支付成功';
                } else {
                    return '支付失败';
                }
            }
        }, {
            field: 'createTime',
            title: '订单创建时间',
            formatter: function (value) {
                return $filter('date')(value,'yyyy-MM-dd HH:mm:ss');
            }
        }, {
            field: 'pTime',
            title: '订单交易时间',
            formatter: function (value) {
                return $filter('date')(value,'yyyy-MM-dd HH:mm:ss');
            }
        }]
    });
    $scope.select = function () {
        var merNo = $('#merNo').val();
        var platformNo = $('#platformNo').val();
        var payNo = $('#payNo').val();
        $http({
            url: "select/one",
            contentType: "application/x-www-form-urlencoded",
            method: 'POST',
            params: {
                merNo: merNo,
                platformNo: platformNo,
                payNo: payNo
            }
        }).success(function (ret) {
            if (ret.state) {
                $scope.totalData = ret;
                $scope.displayData(ret.data)
            } else {
                alert(ret.errorMsg)
            }
        }).error(function (data) {
        });
    }
    $scope.displayData = function (data) {
        $("#table").bootstrapTable("load", data)
    }
});
app.controller('reCallbackController', function ($scope, $http) {
    $scope.init = function () {
        $http({
            url: "login/userMsg",
            contentType: "application/x-www-form-urlencoded",
            method: 'POST'
        }).success(function (ret) {
            if (ret.state) {
                $scope.data = ret.data;
            }
        }).error(function (data) {
        });
    }
});
app.controller('totalController', function ($scope, $http) {
    var dateOption = {format: 'yyyy/m/d', autoclose: true, language: "zh-CN"}
    var date = new Date();
    var dataFormat = date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate();
    $('#begin_time').datepicker(dateOption);
    $('#end_time').datepicker(dateOption);
    $('#begin_time').val(dataFormat);
    $('#end_time').val(dataFormat);
    var startNow = null;
    var endNow = null;
    $("#table").bootstrapTable({
        striped: true,//是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        columns: [{
            field: 'Number',
            title: '行号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {
            field: 'date',
            title: '日期'
        }, {
            field: 'merId',
            title: '商户号'
        }, {
            field: 'payType',
            title: '支付方式'
        }, {
            field: 'totalMoney',
            title: '成功金额'
        }, {
            field: 'settlement',
            title: '结算金额'
        }, {
            field: 'successCount',
            title: '成功单数'
        }]
    });
    $scope.select = function () {
        var startTime = $("#begin_time").val();
        var endTime = $("#end_time").val();
        if (startTime == startNow && endTime == endNow) {
            return
        }
        startNow = $("#begin_time").val();
        endNow = $("#end_time").val();
        $http({
            url: "summary/total",
            contentType: "application/x-www-form-urlencoded",
            method: 'POST',
            params: {
                startTime: startTime,
                endTime: endTime
            }
        }).success(function (ret) {
            if (ret.state) {
                $scope.totalData = ret;
                $scope.displayData(ret.data)
            } else {
                alert(ret.errorMsg)
            }
        }).error(function (data) {
        });
    };
    $scope.displayData = function (data) {
        $("#table").bootstrapTable("load", data)
    }
});
app.controller('appController', function ($scope, $http) {
    $http({
        url: "login/timeout",
        contentType: "application/x-www-form-urlencoded",
        method: 'POST'
    }).success(function (ret) {
        if (200 == ret.state) {
            $scope.username = ret.data;
        } else {
            window.location.href = "login.html";
        }
    }).error(function (data) {
    });
    $scope.outLogin = function () {
        $http({
            url: "login/outLogin",
            contentType: "application/x-www-form-urlencoded",
            method: 'GET'
        }).success(function (ret) {
            if (200 == ret.state) {
                $scope.username = ret.data;
            } else {
                window.location.href = "login.html";
            }
        }).error(function (data) {
        });
    }
});
app.controller('changePassWordController', function ($scope, $http) {
    $scope.changePwd = function () {
        var oldPwd = $('#old_pwd').val();
        var newPwd = $('#new_pwd').val();
        var newPwdCfm = $('#new_pwd_confirm').val();
        if (newPwd != newPwdCfm) {
            alert("两次输入的新密码不一致")
            return
        }
        $http({
            url: "login/changePwd",
            contentType: "application/x-www-form-urlencoded",
            method: 'POST',
            params: {
                oldPassWord: oldPwd,
                newPassWord: newPwd
            }
        }).success(function (ret) {
            alert(ret.msg);
            if (200 == ret.state) {
                window.location.href = "login.html";
            }
        }).error(function (data) {
        });
    }
});

