let api = [];
const apiDocListSize = 1
api.push({
    name: 'default',
    order: '1',
    list: []
})
api[0].list.push({
    alias: 'EmployeeAttendanceController',
    order: '1',
    link: '&lt;p&gt;  员工考勤表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  员工考勤表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'MedicationPurchaseController',
    order: '2',
    link: '&lt;p&gt;  药品采购表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  药品采购表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'AnnouncementController',
    order: '3',
    link: '&lt;p&gt;  公告表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  公告表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'UserRegistrationController',
    order: '4',
    link: '&lt;p&gt;  用户挂号表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  用户挂号表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'AlipayController',
    order: '5',
    link: '支付宝支付控制器',
    desc: '支付宝支付控制器',
    list: []
})
api[0].list[4].list.push({
    order: '1',
    deprecated: 'false',
    url: 'http://127.0.0.1/alipay/pcPayment',
    desc: '',
});
api[0].list[4].list.push({
    order: '2',
    deprecated: 'false',
    url: 'http://127.0.0.1/alipay/mobilePayment',
    desc: '',
});
api[0].list[4].list.push({
    order: '3',
    deprecated: 'false',
    url: 'http://127.0.0.1/alipay/notify',
    desc: '',
});
api[0].list[4].list.push({
    order: '4',
    deprecated: 'false',
    url: 'http://127.0.0.1/alipay/queryPaymentStatus',
    desc: '',
});
api[0].list.push({
    alias: 'DepartmentController',
    order: '6',
    link: '&lt;p&gt;  科室信息表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  科室信息表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'RoomController',
    order: '7',
    link: '&lt;p&gt;  病房信息表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  病房信息表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'HospitalizationInfoController',
    order: '8',
    link: '&lt;p&gt;  住院信息表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  住院信息表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'NurseScheduleController',
    order: '9',
    link: '&lt;p&gt;  护士排班表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  护士排班表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'UserRoleRelationsController',
    order: '10',
    link: '&lt;p&gt;  用户角色关系表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  用户角色关系表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'NurseInfoController',
    order: '11',
    link: '&lt;p&gt;  护士信息表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  护士信息表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'BedController',
    order: '12',
    link: '&lt;p&gt;  床位信息表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  床位信息表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'PatientRelativeController',
    order: '13',
    link: '&lt;p&gt;  患者家属信息表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  患者家属信息表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'AdminInfoController',
    order: '14',
    link: '&lt;p&gt;  管理员信息表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  管理员信息表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'MedicalRecordController',
    order: '15',
    link: '&lt;p&gt;  病历信息表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  病历信息表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'DoctorInfoController',
    order: '16',
    link: '&lt;p&gt;  医生信息表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  医生信息表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'SystemRolesController',
    order: '17',
    link: '&lt;p&gt;  系统角色表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  系统角色表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'RoleResourceRelationsController',
    order: '18',
    link: '&lt;p&gt;  角色资源关系表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  角色资源关系表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'EmployeeInfoController',
    order: '19',
    link: '&lt;p&gt;  员工信息表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  员工信息表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'UserInfoController',
    order: '20',
    link: '&lt;p&gt;  用户信息表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  用户信息表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'UserFeedbackController',
    order: '21',
    link: '&lt;p&gt;  用户反馈表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  用户反馈表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'PrescriptionInfoController',
    order: '22',
    link: '&lt;p&gt;  处方信息表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  处方信息表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'SystemResourcesController',
    order: '23',
    link: '&lt;p&gt;  系统资源表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  系统资源表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'UserAppointmentController',
    order: '24',
    link: '&lt;p&gt;  用户预约表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  用户预约表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'MedicationStockController',
    order: '25',
    link: '&lt;p&gt;  药品入库作业表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  药品入库作业表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'FinancialInfoController',
    order: '26',
    link: '&lt;p&gt;  医院财务表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  医院财务表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'MedicationInfoController',
    order: '27',
    link: '&lt;p&gt;  药品信息表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  药品信息表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'MedicationFinancialAuditController',
    order: '28',
    link: '&lt;p&gt;  药品财务审核表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  药品财务审核表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'MedicationPurchaseDetailsController',
    order: '29',
    link: '&lt;p&gt;  药品采购明细表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  药品采购明细表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'AlipayOrderController',
    order: '30',
    link: '&lt;p&gt;  支付宝支付订单表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  支付宝支付订单表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'PrescriptionDrugController',
    order: '31',
    link: '&lt;p&gt;  处方药品关联表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  处方药品关联表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'PositionInfoController',
    order: '32',
    link: '&lt;p&gt;  职位信息表_前端控制器  &lt;/p&gt;',
    desc: '&lt;p&gt;  职位信息表 前端控制器  &lt;/p&gt;',
    list: []
})
api[0].list.push({
    alias: 'error',
    order: '33',
    link: 'error_code_list',
    desc: '错误码列表',
    list: []
})
api[0].list.push({
    alias: 'dict',
    order: '34',
    link: 'dict_list',
    desc: '数据字典',
    list: []
})
api[0].list[33].list.push({
    order: '1',
    deprecated: 'false',
    url: '',
    desc: 'http状态码字典',
});
document.onkeydown = keyDownSearch;
function keyDownSearch(e) {
    const theEvent = e;
    const code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code === 13) {
        const search = document.getElementById('search');
        const searchValue = search.value.toLocaleLowerCase();

        let searchGroup = [];
        for (let i = 0; i < api.length; i++) {

            let apiGroup = api[i];

            let searchArr = [];
            for (let i = 0; i < apiGroup.list.length; i++) {
                let apiData = apiGroup.list[i];
                const desc = apiData.desc;
                if (desc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                    searchArr.push({
                        order: apiData.order,
                        desc: apiData.desc,
                        link: apiData.link,
                        list: apiData.list
                    });
                } else {
                    let methodList = apiData.list || [];
                    let methodListTemp = [];
                    for (let j = 0; j < methodList.length; j++) {
                        const methodData = methodList[j];
                        const methodDesc = methodData.desc;
                        if (methodDesc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                            methodListTemp.push(methodData);
                            break;
                        }
                    }
                    if (methodListTemp.length > 0) {
                        const data = {
                            order: apiData.order,
                            desc: apiData.desc,
                            link: apiData.link,
                            list: methodListTemp
                        };
                        searchArr.push(data);
                    }
                }
            }
            if (apiGroup.name.toLocaleLowerCase().indexOf(searchValue) > -1) {
                searchGroup.push({
                    name: apiGroup.name,
                    order: apiGroup.order,
                    list: searchArr
                });
                continue;
            }
            if (searchArr.length === 0) {
                continue;
            }
            searchGroup.push({
                name: apiGroup.name,
                order: apiGroup.order,
                list: searchArr
            });
        }
        let html;
        if (searchValue === '') {
            const liClass = "";
            const display = "display: none";
            html = buildAccordion(api,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        } else {
            const liClass = "open";
            const display = "display: block";
            html = buildAccordion(searchGroup,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        }
        const Accordion = function (el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;
            const links = this.el.find('.dd');
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
        };
        Accordion.prototype.dropdown = function (e) {
            const $el = e.data.el;
            let $this = $(this), $next = $this.next();
            $next.slideToggle();
            $this.parent().toggleClass('open');
            if (!e.data.multiple) {
                $el.find('.submenu').not($next).slideUp("20").parent().removeClass('open');
            }
        };
        new Accordion($('#accordion'), false);
    }
}

function buildAccordion(apiGroups, liClass, display) {
    let html = "";
    if (apiGroups.length > 0) {
        if (apiDocListSize === 1) {
            let apiData = apiGroups[0].list;
            let order = apiGroups[0].order;
            for (let j = 0; j < apiData.length; j++) {
                html += '<li class="'+liClass+'">';
                html += '<a class="dd" href="#_'+order+'_'+apiData[j].order+'_' + apiData[j].link + '">' + apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
                html += '<ul class="sectlevel2" style="'+display+'">';
                let doc = apiData[j].list;
                for (let m = 0; m < doc.length; m++) {
                    let spanString;
                    if (doc[m].deprecated === 'true') {
                        spanString='<span class="line-through">';
                    } else {
                        spanString='<span>';
                    }
                    html += '<li><a href="#_'+order+'_' + apiData[j].order + '_' + doc[m].order + '_' + doc[m].desc + '">' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + spanString + doc[m].desc + '<span></a> </li>';
                }
                html += '</ul>';
                html += '</li>';
            }
        } else {
            for (let i = 0; i < apiGroups.length; i++) {
                let apiGroup = apiGroups[i];
                html += '<li class="'+liClass+'">';
                html += '<a class="dd" href="#_'+apiGroup.order+'_' + apiGroup.name + '">' + apiGroup.order + '.&nbsp;' + apiGroup.name + '</a>';
                html += '<ul class="sectlevel1">';

                let apiData = apiGroup.list;
                for (let j = 0; j < apiData.length; j++) {
                    html += '<li class="'+liClass+'">';
                    html += '<a class="dd" href="#_'+apiGroup.order+'_'+ apiData[j].order + '_'+ apiData[j].link + '">' +apiGroup.order+'.'+ apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
                    html += '<ul class="sectlevel2" style="'+display+'">';
                    let doc = apiData[j].list;
                    for (let m = 0; m < doc.length; m++) {
                       let spanString;
                       if (doc[m].deprecated === 'true') {
                           spanString='<span class="line-through">';
                       } else {
                           spanString='<span>';
                       }
                       html += '<li><a href="#_'+apiGroup.order+'_' + apiData[j].order + '_' + doc[m].order + '_' + doc[m].desc + '">'+apiGroup.order+'.' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + spanString + doc[m].desc + '<span></a> </li>';
                   }
                    html += '</ul>';
                    html += '</li>';
                }

                html += '</ul>';
                html += '</li>';
            }
        }
    }
    return html;
}