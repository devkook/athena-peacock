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
                                    xtype: 'actioncolumn',
                                    align: 'center',
                                    items: [
                                        {
                                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                                alert("Delete "+ record.get("softwareId"));
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
                    title: 'OS Package',
                    items: [
                        {
                            xtype: 'gridpanel',
                            id: 'instOSPkgGrid',
                            header: false,
                            title: 'My Grid Panel',
                            store: 'OSPackageListStore',
                            columns: [
                                {
                                    xtype: 'rownumberer'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'pkg_name',
                                    text: 'Name'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'pkg_arch',
                                    text: 'Arch'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'version',
                                    text: 'Version'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'release',
                                    text: 'Release'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'size',
                                    text: 'Size'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'summary',
                                    text: 'Summary'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 200,
                                    dataIndex: 'description',
                                    text: 'Description'
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
                                    constrain: true,
                                    dateFormat: 'm/d G:i',
                                    step: [
                                        'h',
                                        1/3
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
                                        'h',
                                        1/3
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
                                        'h',
                                        1/3
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
                                        'h',
                                        1/3
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
                                        'h',
                                        1/3
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

    onChartMouseEnter: function(e, eOpts) {
        Peacock.app.debug("IdleCPUChart.onChartMouseEnter.");


        //alert(Ext.getClassName(this));//InstanceTabPanel


        //Ext.getCmp("monChart1").getEl().setStyle("borderWidth", "3px");

    },

    onMonChart1MouseLeave: function(e, eOpts) {

        //Ext.getCmp("monChart1").getEl().setStyle("borderWidth", "0px");
    }

});