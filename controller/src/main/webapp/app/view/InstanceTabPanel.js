/*
 * File: app/view/InstanceTabPanel.js
 *
 * This file was generated by Sencha Architect version 2.2.3.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 4.2.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 4.2.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('Peacock.view.InstanceTabPanel', {
    extend: 'Ext.tab.Panel',
    alias: 'widget.instanceTabPanel',

    height: 325,
    width: 907,
    activeTab: 0,
    plain: true,

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    height: 214,
                    width: 400,
                    autoScroll: true,
                    layout: {
                        type: 'fit'
                    },
                    title: 'Description',
                    items: [
                        {
                            xtype: 'form',
                            id: 'instDescForm',
                            defaults: {
                                border: false,
                                xtype: 'panel',
                                flex: 1,
                                layout: 'anchor'
                            },
                            bodyPadding: 10,
                            bodyStyle: 'padding:5px 5px 0',
                            header: false,
                            title: 'My Form',
                            fieldDefaults: {
                                msgTarget: 'side',
                                margin: '0 10',
                                readOnly: true
                            },
                            items: [
                                {
                                    xtype: 'fieldcontainer',
                                    height: 34,
                                    defaults: {
                                        flex: 1
                                    },
                                    layout: {
                                        align: 'middle',
                                        type: 'hbox'
                                    },
                                    fieldLabel: 'Label',
                                    hideLabel: true,
                                    items: [
                                        {
                                            xtype: 'textfield',
                                            fieldLabel: 'ID',
                                            name: 'machineId'
                                        },
                                        {
                                            xtype: 'textfield',
                                            fieldLabel: 'Host',
                                            name: 'hostName'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'fieldcontainer',
                                    height: 34,
                                    defaults: {
                                        flex: 1
                                    },
                                    layout: {
                                        align: 'middle',
                                        type: 'hbox'
                                    },
                                    fieldLabel: 'Label',
                                    hideLabel: true,
                                    items: [
                                        {
                                            xtype: 'textfield',
                                            fieldLabel: 'OS Name',
                                            name: 'osName'
                                        },
                                        {
                                            xtype: 'textfield',
                                            fieldLabel: 'OS Version',
                                            name: 'osVer'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'fieldcontainer',
                                    height: 34,
                                    defaults: {
                                        flex: 1
                                    },
                                    layout: {
                                        align: 'middle',
                                        type: 'hbox'
                                    },
                                    fieldLabel: 'Label',
                                    hideLabel: true,
                                    items: [
                                        {
                                            xtype: 'textfield',
                                            fieldLabel: 'Mac Address',
                                            name: 'machineMacAddr'
                                        },
                                        {
                                            xtype: 'checkboxfield',
                                            flex: 1,
                                            fieldLabel: 'Is VM',
                                            name: 'isVm',
                                            boxLabel: 'VM',
                                            inputValue: 'Y',
                                            uncheckedValue: 'N'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'fieldcontainer',
                                    height: 34,
                                    defaults: {
                                        flex: 1
                                    },
                                    layout: {
                                        align: 'middle',
                                        type: 'hbox'
                                    },
                                    fieldLabel: 'Label',
                                    hideLabel: true,
                                    items: [
                                        {
                                            xtype: 'textfield',
                                            fieldLabel: 'IP Address',
                                            name: 'ipAddr'
                                        },
                                        {
                                            xtype: 'textfield',
                                            fieldLabel: 'OS Arch',
                                            name: 'osArch'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'fieldcontainer',
                                    height: 34,
                                    defaults: {
                                        flex: 1
                                    },
                                    layout: {
                                        align: 'middle',
                                        type: 'hbox'
                                    },
                                    fieldLabel: 'Label',
                                    hideLabel: true,
                                    items: [
                                        {
                                            xtype: 'textfield',
                                            fieldLabel: 'CPU Clock',
                                            name: 'cpuClock'
                                        },
                                        {
                                            xtype: 'textfield',
                                            fieldLabel: 'Core',
                                            name: 'cpuNum'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'fieldcontainer',
                                    height: 34,
                                    defaults: {
                                        flex: 1
                                    },
                                    layout: {
                                        align: 'middle',
                                        type: 'hbox'
                                    },
                                    fieldLabel: 'Label',
                                    hideLabel: true,
                                    items: [
                                        {
                                            xtype: 'textfield',
                                            fieldLabel: 'Memory Size',
                                            name: 'memSize'
                                        },
                                        {
                                            xtype: 'textfield',
                                            fieldLabel: 'Create Time',
                                            name: 'regDt'
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    title: 'Software',
                    items: [
                        {
                            xtype: 'gridpanel',
                            id: 'instSoftGrid',
                            header: false,
                            title: 'My Grid Panel',
                            store: 'SoftwareListStore',
                            columns: [
                                {
                                    xtype: 'rownumberer'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 200,
                                    dataIndex: 'softwareName',
                                    text: 'Name'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'softwareVersion',
                                    text: 'Version'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 200,
                                    dataIndex: 'regDt',
                                    text: 'Install Date'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 200,
                                    defaultWidth: 200,
                                    dataIndex: 'description',
                                    text: 'Description'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'installStat',
                                    text: 'Status'
                                },
                                {
                                    xtype: 'actioncolumn',
                                    align: 'center',
                                    menuText: 'menuTest',
                                    items: [
                                        {
                                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                                var win = Ext.widget("swLogWindow");

                                                win.swid = record.get("softwareId");

                                                win.show();
                                            },
                                            icon: 'resources/icons/fam/application_view_list.png',
                                            iconCls: 'action-column-icon',
                                            tooltip: 'veiw install log'
                                        },
                                        {
                                            handler: function(view, rowIndex, colIndex, item, e, record, row) {

                                                Ext.MessageBox.confirm('Confirm', '삭제 하시겠습니까?', function(btn){

                                                    if(btn == "yes"){
                                                        view.setLoading(true);
                                                        Ext.Ajax.request({
                                                            url: 'software/remove',
                                                            params : {
                                                                machineId : Peacock.app.selectedRecord.get("machineId"),
                                                                softwareId : record.get("softwareId")
                                                            },
                                                            disableCaching : true,
                                                            success: function(response){
                                                                //var text = response.responseText;
                                                                //alert(text);
                                                                view.setLoading(false);
                                                                view.getStore().reload();
                                                            }
                                                        });
                                                    }

                                                });
                                            },
                                            icon: 'resources/icons/fam/delete.gif',
                                            tooltip: 'Delete'
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    layout: {
                        type: 'fit'
                    },
                    title: 'OS Package',
                    items: [
                        {
                            xtype: 'gridpanel',
                            data: [
                                [
                                    '11',
                                    '222',
                                    '3333',
                                    '4444'
                                ]
                            ],
                            id: 'instOSPkgGrid',
                            autoScroll: true,
                            bodyBorder: true,
                            header: false,
                            title: 'My Grid Panel',
                            columnLines: true,
                            forceFit: false,
                            store: 'OSPackageListStore',
                            columns: [
                                {
                                    xtype: 'rownumberer'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 200,
                                    dataIndex: 'name',
                                    text: 'Name'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'arch',
                                    text: 'Arch'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'version',
                                    text: 'Version'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'releaseInfo',
                                    text: 'Release'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'size',
                                    text: 'Size'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 200,
                                    dataIndex: 'summary',
                                    text: 'Summary'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 300,
                                    dataIndex: 'description',
                                    text: 'Description'
                                }
                            ],
                            dockedItems: [
                                {
                                    xtype: 'pagingtoolbar',
                                    dock: 'bottom',
                                    width: 360,
                                    displayInfo: true,
                                    store: 'OSPackageListStore'
                                },
                                {
                                    xtype: 'toolbar',
                                    dock: 'top',
                                    items: [
                                        {
                                            xtype: 'button',
                                            text: 'Reload',
                                            tooltip: '패키지 정보 재 수집 요청',
                                            listeners: {
                                                click: {
                                                    fn: me.onButtonClick,
                                                    scope: me
                                                }
                                            }
                                        },
                                        {
                                            xtype: 'tbseparator'
                                        },
                                        {
                                            xtype: 'textfield',
                                            fieldLabel: 'Name :',
                                            labelWidth: 45,
                                            name: 'name',
                                            enableKeyEvents: true,
                                            listeners: {
                                                specialkey: {
                                                    fn: me.onTextfieldSpecialkey,
                                                    scope: me
                                                }
                                            }
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    height: 289,
                    width: 755,
                    autoScroll: true,
                    title: 'Monitoring',
                    items: [
                        {
                            xtype: 'chart',
                            border: 0,
                            cls: '',
                            height: 250,
                            id: 'monChart1',
                            style: {
                                borderColor: 'red',
                                borderStyle: 'solid'
                            },
                            width: 311,
                            animate: true,
                            insetPadding: 20,
                            store: 'ChartIdleCPUStore',
                            axes: [
                                {
                                    type: 'Time',
                                    fields: [
                                        'regDt'
                                    ],
                                    position: 'bottom',
                                    title: 'Idle CPU',
                                    dateFormat: 'm/d G:i',
                                    step: [
                                        'mi',
                                        1
                                    ]
                                },
                                {
                                    type: 'Numeric',
                                    fields: [
                                        'monDataValue'
                                    ],
                                    position: 'left'
                                }
                            ],
                            series: [
                                {
                                    type: 'line',
                                    xField: 'regDt',
                                    yField: 'monDataValue',
                                    showMarkers: false,
                                    smooth: 3
                                }
                            ],
                            listeners: {
                                mouseenter: {
                                    fn: me.onChartMouseEnter,
                                    scope: me
                                },
                                mouseleave: {
                                    fn: me.onMonChart1MouseLeave,
                                    scope: me
                                }
                            }
                        },
                        {
                            xtype: 'chart',
                            height: 250,
                            width: 311,
                            animate: true,
                            insetPadding: 20,
                            store: 'ChartCombCPUStore',
                            axes: [
                                {
                                    type: 'Time',
                                    fields: [
                                        'regDt'
                                    ],
                                    position: 'bottom',
                                    title: 'Combined CPU',
                                    dateFormat: 'm/d G:i',
                                    step: [
                                        'mi',
                                        1
                                    ]
                                },
                                {
                                    type: 'Numeric',
                                    fields: [
                                        'monDataValue'
                                    ],
                                    position: 'left'
                                }
                            ],
                            series: [
                                {
                                    type: 'line',
                                    xField: 'regDt',
                                    yField: 'monDataValue',
                                    showMarkers: false,
                                    smooth: 3
                                }
                            ]
                        },
                        {
                            xtype: 'chart',
                            height: 250,
                            width: 311,
                            animate: true,
                            insetPadding: 20,
                            store: 'ChartTotalMemStore',
                            axes: [
                                {
                                    type: 'Time',
                                    fields: [
                                        'regDt'
                                    ],
                                    position: 'bottom',
                                    title: 'Total Memory',
                                    dateFormat: 'm/d G:i',
                                    step: [
                                        'mi',
                                        1
                                    ]
                                },
                                {
                                    type: 'Numeric',
                                    fields: [
                                        'monDataValue'
                                    ],
                                    position: 'left'
                                }
                            ],
                            series: [
                                {
                                    type: 'line',
                                    xField: 'regDt',
                                    yField: 'monDataValue',
                                    showMarkers: false,
                                    smooth: 3
                                }
                            ]
                        },
                        {
                            xtype: 'chart',
                            height: 250,
                            width: 311,
                            animate: true,
                            insetPadding: 20,
                            store: 'ChartFreeMemStore',
                            axes: [
                                {
                                    type: 'Time',
                                    fields: [
                                        'regDt'
                                    ],
                                    position: 'bottom',
                                    title: 'Free Memory',
                                    dateFormat: 'm/d G:i',
                                    step: [
                                        'mi',
                                        1
                                    ]
                                },
                                {
                                    type: 'Numeric',
                                    fields: [
                                        'monDataValue'
                                    ],
                                    position: 'left'
                                }
                            ],
                            series: [
                                {
                                    type: 'line',
                                    xField: 'regDt',
                                    yField: 'monDataValue',
                                    showMarkers: false,
                                    smooth: 3
                                }
                            ]
                        },
                        {
                            xtype: 'chart',
                            height: 250,
                            width: 311,
                            animate: true,
                            insetPadding: 20,
                            store: 'ChartUsedMemStore',
                            axes: [
                                {
                                    type: 'Time',
                                    fields: [
                                        'regDt'
                                    ],
                                    position: 'bottom',
                                    title: 'Used Memory',
                                    dateFormat: 'm/d G:i',
                                    step: [
                                        'mi',
                                        1
                                    ]
                                },
                                {
                                    type: 'Numeric',
                                    fields: [
                                        'monDataValue'
                                    ],
                                    position: 'left'
                                }
                            ],
                            series: [
                                {
                                    type: 'line',
                                    xField: 'regDt',
                                    yField: 'monDataValue',
                                    showMarkers: false,
                                    smooth: 3
                                }
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    },

    onButtonClick: function(button, e, eOpts) {

        Ext.MessageBox.confirm('Confirm', '패키지 정보를 재 수집합니다. 하시겠습니까?', function(btn){

            if(btn == "yes"){

                Ext.Ajax.request({
                    url: "package/reload",
                    params: {
                        machineId : Peacock.app.selectedRecord.get("machineId")
                    },
                    waitMsg: 'request package reload...',
                    success: function(response){
                        var msg = Ext.JSON.decode(response.responseText).msg;
                        Ext.MessageBox.alert('알림', msg);

                    }
                });
            }

        });
    },

    onTextfieldSpecialkey: function(field, e, eOpts) {
        if(e.getKey() == e.ENTER){

            var gridStore = field.up("gridpanel").getStore();

            gridStore.getProxy().setExtraParam( "name", field.getRawValue() );

            gridStore.load();
        }
    },

    onChartMouseEnter: function(e, eOpts) {
        Peacock.app.debug("IdleCPUChart.onChartMouseEnter.");


        //alert(Ext.getClassName(this));//InstanceTabPanel


        //Ext.getCmp("monChart1").getEl().setStyle("borderWidth", "3px");

    },

    onMonChart1MouseLeave: function(e, eOpts) {

        //Ext.getCmp("monChart1").getEl().setStyle("borderWidth", "0px");
    }

});