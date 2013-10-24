/*
 * File: app/view/MainViewport.js
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

Ext.define('Peacock.view.MainViewport', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.mainView',

    layout: {
        type: 'card'
    },

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    header: false,
                    title: 'My Panel',
                    listeners: {
                        activate: {
                            fn: me.onPanelActivate,
                            scope: me
                        }
                    }
                },
                {
                    xtype: 'container',
                    width: 150,
                    layout: {
                        type: 'border'
                    },
                    items: [
                        {
                            xtype: 'panel',
                            margins: '0 0 5 0',
                            region: 'north',
                            height: 40,
                            layout: {
                                align: 'middle',
                                type: 'hbox'
                            },
                            header: false,
                            title: 'TopPanel',
                            items: [
                                {
                                    xtype: 'image',
                                    margins: '0 7',
                                    height: 38,
                                    src: 'resources/images/osc_logo_small.PNG'
                                },
                                {
                                    xtype: 'tbspacer',
                                    flex: 2
                                },
                                {
                                    xtype: 'panel',
                                    flex: 1,
                                    weight: 150,
                                    padding: '0 10',
                                    header: false,
                                    title: 'My Panel',
                                    dockedItems: [
                                        {
                                            xtype: 'toolbar',
                                            dock: 'top',
                                            id: 'topToolbar',
                                            items: [
                                                {
                                                    xtype: 'tbspacer',
                                                    flex: 1
                                                },
                                                {
                                                    xtype: 'splitbutton',
                                                    text: 'idkbj@naver.com',
                                                    menu: {
                                                        xtype: 'menu',
                                                        width: 120,
                                                        items: [
                                                            {
                                                                xtype: 'menuitem',
                                                                id: 'topMyAccountMenu',
                                                                text: 'My Account'
                                                            },
                                                            {
                                                                xtype: 'menuitem',
                                                                id: 'topLogOutMenu',
                                                                text: 'Log Out'
                                                            }
                                                        ]
                                                    }
                                                },
                                                {
                                                    xtype: 'tbseparator'
                                                },
                                                {
                                                    xtype: 'tbtext',
                                                    text: '관리자'
                                                }
                                            ]
                                        }
                                    ]
                                }
                            ]
                        },
                        {
                            xtype: 'panel',
                            region: 'west',
                            split: true,
                            id: 'westPanel',
                            maxWidth: 500,
                            minWidth: 150,
                            width: 220,
                            layout: {
                                type: 'accordion'
                            },
                            collapsible: true,
                            title: 'Menus',
                            items: [
                                {
                                    xtype: 'treepanel',
                                    title: 'Server Management',
                                    titleCollapse: false,
                                    lines: false,
                                    root: {
                                        text: 'Root',
                                        expanded: true,
                                        children: [
                                            {
                                                text: 'Instance Dashboard',
                                                id: 'inst-dash',
                                                leaf: true
                                            },
                                            {
                                                text: 'Instances',
                                                id: 'insts',
                                                leaf: true
                                            }
                                        ]
                                    },
                                    rootVisible: false,
                                    viewConfig: {

                                    }
                                },
                                {
                                    xtype: 'treepanel',
                                    title: 'RHEV MANAGER',
                                    root: {
                                        text: 'Root',
                                        expanded: true,
                                        children: [
                                            {
                                                text: 'Virtual Machines',
                                                id: 'rhevm-vms',
                                                leaf: true
                                            },
                                            {
                                                text: 'Templates',
                                                id: 'rhevm-tmps',
                                                leaf: true
                                            }
                                        ]
                                    },
                                    rootVisible: false,
                                    viewConfig: {

                                    }
                                },
                                {
                                    xtype: 'treepanel',
                                    width: 100,
                                    title: 'SCALING',
                                    root: {
                                        text: 'Root',
                                        expanded: true,
                                        children: [
                                            {
                                                text: 'Scaling Groups',
                                                id: 'scal-grp',
                                                leaf: true
                                            },
                                            {
                                                text: 'Load Balancers',
                                                id: 'scal-lb',
                                                leaf: true
                                            }
                                        ]
                                    },
                                    rootVisible: false,
                                    viewConfig: {

                                    }
                                },
                                {
                                    xtype: 'treepanel',
                                    height: 150,
                                    title: 'Manage User/Group',
                                    root: {
                                        text: 'Root',
                                        expanded: true,
                                        children: [
                                            {
                                                text: 'Groups',
                                                id: 'user-grp',
                                                leaf: true
                                            },
                                            {
                                                text: 'Users',
                                                id: 'users',
                                                leaf: true
                                            }
                                        ]
                                    },
                                    rootVisible: false,
                                    viewConfig: {

                                    }
                                }
                            ]
                        },
                        {
                            xtype: 'container',
                            region: 'center',
                            id: 'centerContainer',
                            layout: {
                                type: 'card'
                            },
                            items: [
                                {
                                    xtype: 'panel',
                                    layout: {
                                        align: 'stretch',
                                        type: 'vbox'
                                    },
                                    title: 'Machine status',
                                    items: [
                                        {
                                            xtype: 'panel',
                                            flex: 1,
                                            layout: {
                                                align: 'middle',
                                                pack: 'center',
                                                padding: 10,
                                                type: 'hbox'
                                            },
                                            header: false,
                                            title: 'Machine Board',
                                            items: [
                                                {
                                                    xtype: 'image',
                                                    margins: '0 20',
                                                    src: 'resources/images/nestedList.png'
                                                },
                                                {
                                                    xtype: 'label',
                                                    margins: '0 5 0 0',
                                                    style: 'font-size : 16px',
                                                    text: 'Total Instances : '
                                                },
                                                {
                                                    xtype: 'label',
                                                    style: 'font-size : 16px; font-weight : bold',
                                                    text: '2,345'
                                                },
                                                {
                                                    xtype: 'image',
                                                    margins: '0 20 0 60',
                                                    src: 'resources/images/picker.png'
                                                },
                                                {
                                                    xtype: 'label',
                                                    margins: '0 5 0 0',
                                                    style: 'font-size : 16px;',
                                                    text: 'Total VM : '
                                                },
                                                {
                                                    xtype: 'label',
                                                    style: 'font-size : 16px; font-weight : bold',
                                                    text: '3,456'
                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'panel',
                                            flex: 2,
                                            layout: {
                                                align: 'stretch',
                                                type: 'hbox'
                                            },
                                            title: 'Resources status',
                                            items: [
                                                {
                                                    xtype: 'chart',
                                                    flex: 1,
                                                    height: 250,
                                                    width: 400,
                                                    shadow: true,
                                                    animate: true,
                                                    insetPadding: 35,
                                                    store: 'DashGuage1Store',
                                                    axes: [
                                                        {
                                                            position: 'gauge',
                                                            type: 'Gauge',
                                                            margin: 8,
                                                            maximum: 100,
                                                            minimum: 0,
                                                            title: 'CPU'
                                                        }
                                                    ],
                                                    series: [
                                                        me.processMyGaugeSeries3({
                                                            type: 'gauge',
                                                            showInLegend: true,
                                                            tips: {
                                                                trackMouse: true,
                                                                width: 140,
                                                                height: 28,
                                                                renderer: function(storeItem, item) {
                                                                    
                                                                    this.setTitle('Used CPU : ' + storeItem.get('data1') + '%');
                                                                }
                                                            },
                                                            angleField: 'data1',
                                                            donut: 30
                                                        })
                                                    ]
                                                },
                                                {
                                                    xtype: 'chart',
                                                    flex: 1,
                                                    height: 250,
                                                    width: 400,
                                                    shadow: true,
                                                    animate: true,
                                                    insetPadding: 35,
                                                    store: 'DashGuage2Store',
                                                    axes: [
                                                        {
                                                            position: 'gauge',
                                                            type: 'Gauge',
                                                            margin: 8,
                                                            maximum: 100,
                                                            minimum: 0,
                                                            title: 'Memory'
                                                        }
                                                    ],
                                                    series: [
                                                        me.processMyGaugeSeries4({
                                                            type: 'gauge',
                                                            tips: {
                                                                trackMouse: true,
                                                                width: 140,
                                                                height: 28,
                                                                renderer: function(storeItem, item) {
                                                                    
                                                                    this.setTitle('Used Memory : ' + storeItem.get('data1') + '%');
                                                                }
                                                            },
                                                            angleField: 'data1',
                                                            donut: 30
                                                        })
                                                    ]
                                                },
                                                {
                                                    xtype: 'chart',
                                                    flex: 1,
                                                    height: 250,
                                                    width: 400,
                                                    shadow: true,
                                                    animate: true,
                                                    insetPadding: 35,
                                                    store: 'DashGuage3Store',
                                                    axes: [
                                                        {
                                                            position: 'gauge',
                                                            type: 'Gauge',
                                                            margin: 8,
                                                            maximum: 100,
                                                            minimum: 0,
                                                            title: 'Disk'
                                                        }
                                                    ],
                                                    series: [
                                                        me.processMyGaugeSeries5({
                                                            type: 'gauge',
                                                            tips: {
                                                                trackMouse: true,
                                                                width: 140,
                                                                height: 28,
                                                                renderer: function(storeItem, item) {
                                                                    
                                                                    this.setTitle('Used Disk : ' + storeItem.get('data1') + '%');
                                                                }
                                                            },
                                                            angleField: 'data1',
                                                            donut: 30
                                                        })
                                                    ]
                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'gridpanel',
                                            flex: 3,
                                            title: 'Software status',
                                            columnLines: true,
                                            forceFit: false,
                                            store: 'DashSoftwareStore',
                                            columns: [
                                                {
                                                    xtype: 'gridcolumn',
                                                    width: 160,
                                                    dataIndex: 'softwareName',
                                                    text: 'Software'
                                                },
                                                {
                                                    xtype: 'numbercolumn',
                                                    width: 150,
                                                    align: 'center',
                                                    dataIndex: 'installNum',
                                                    text: '설치된 갯수',
                                                    format: '0,000'
                                                },
                                                {
                                                    xtype: 'numbercolumn',
                                                    width: 150,
                                                    align: 'center',
                                                    dataIndex: 'runningNum',
                                                    text: '실행중',
                                                    format: '0,000'
                                                },
                                                {
                                                    xtype: 'numbercolumn',
                                                    width: 150,
                                                    align: 'center',
                                                    dataIndex: 'stopNum',
                                                    text: '비실행',
                                                    format: '0,000'
                                                }
                                            ]
                                        }
                                    ]
                                },
                                {
                                    xtype: 'container',
                                    id: 'borderContainer',
                                    layout: {
                                        type: 'border'
                                    },
                                    items: [
                                        me.processMainGridPanel({
                                            xtype: 'gridpanel',
                                            region: 'center',
                                            id: 'mainGridPanel',
                                            minHeight: 100,
                                            title: 'Grid Panel',
                                            columnLines: true,
                                            columns: [
                                                {
                                                    xtype: 'gridcolumn',
                                                    dataIndex: 'string',
                                                    text: 'String'
                                                }
                                            ],
                                            viewConfig: {
                                                id: 'mainGridView'
                                            },
                                            dockedItems: [
                                                {
                                                    xtype: 'pagingtoolbar',
                                                    dock: 'bottom',
                                                    width: 360,
                                                    displayInfo: true
                                                },
                                                {
                                                    xtype: 'toolbar',
                                                    dock: 'top',
                                                    id: 'mainToolbar',
                                                    items: [
                                                        {
                                                            xtype: 'button',
                                                            id: 'mainButton',
                                                            text: 'MyButton'
                                                        },
                                                        {
                                                            xtype: 'splitbutton',
                                                            text: 'Actions',
                                                            menu: {
                                                                xtype: 'menu',
                                                                id: 'tbActionMenu',
                                                                items: [
                                                                    {
                                                                        xtype: 'menuitem',
                                                                        id: 'tbActionStart',
                                                                        text: 'Start'
                                                                    },
                                                                    {
                                                                        xtype: 'menuitem',
                                                                        id: 'tbActionStop',
                                                                        text: 'Stop'
                                                                    },
                                                                    {
                                                                        xtype: 'menuitem',
                                                                        id: 'tbActionTerminate',
                                                                        text: 'Terminate'
                                                                    },
                                                                    {
                                                                        xtype: 'menuitem',
                                                                        id: 'tbActionEdit',
                                                                        text: 'Edit'
                                                                    },
                                                                    {
                                                                        xtype: 'menuitem',
                                                                        id: 'tbActionDelete',
                                                                        text: 'Delete'
                                                                    },
                                                                    {
                                                                        xtype: 'menuitem',
                                                                        id: 'tbActionRegister',
                                                                        text: 'Register'
                                                                    },
                                                                    {
                                                                        xtype: 'menuitem',
                                                                        id: 'tbActionSWInstall',
                                                                        text: 'S/W Install'
                                                                    }
                                                                ]
                                                            }
                                                        },
                                                        {
                                                            xtype: 'tbseparator',
                                                            margins: '0 15 0 0'
                                                        },
                                                        {
                                                            xtype: 'label',
                                                            text: 'Filtering :'
                                                        },
                                                        {
                                                            xtype: 'textfield',
                                                            fieldLabel: 'Label',
                                                            hideLabel: true,
                                                            enableKeyEvents: true
                                                        }
                                                    ]
                                                }
                                            ]
                                        }),
                                        {
                                            xtype: 'panel',
                                            region: 'south',
                                            split: true,
                                            height: 400,
                                            layout: {
                                                align: 'stretch',
                                                type: 'vbox'
                                            },
                                            title: 'Detail',
                                            items: [
                                                {
                                                    xtype: 'panel',
                                                    flex: 1,
                                                    margin: 5,
                                                    header: false,
                                                    title: 'My Panel',
                                                    items: [
                                                        {
                                                            xtype: 'label',
                                                            id: 'detailTitleLabel',
                                                            style: '',
                                                            text: 'title panel'
                                                        }
                                                    ]
                                                },
                                                {
                                                    xtype: 'panel',
                                                    flex: 10,
                                                    id: 'detailPanel',
                                                    layout: {
                                                        type: 'card'
                                                    },
                                                    header: false,
                                                    title: 'My Panel'
                                                }
                                            ]
                                        }
                                    ]
                                }
                            ]
                        },
                        {
                            xtype: 'container',
                            region: 'south',
                            split: true,
                            height: 30,
                            padding: '',
                            layout: {
                                align: 'stretch',
                                pack: 'center',
                                type: 'vbox'
                            },
                            items: [
                                {
                                    xtype: 'label',
                                    margin: '',
                                    text: '© 2013 , Open Source Consulting, Inc. All rights reserved.'
                                },
                                {
                                    xtype: 'textareafield',
                                    flex: 1,
                                    id: 'debugTextArea',
                                    fieldLabel: 'Label',
                                    hideLabel: true
                                }
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    },

    processMyGaugeSeries3: function(config) {
        config.colorSet = ['#F49D10', '#ddd'];

        return config;
    },

    processMyGaugeSeries4: function(config) {
        config.colorSet = ['#82B525', '#ddd'];
        return config;
    },

    processMyGaugeSeries5: function(config) {
        config.colorSet = ['#3AA8CB', '#ddd'];
        return config;
    },

    processMainGridPanel: function(config) {
        config.loadMask = true;
        return config;
    },

    onPanelActivate: function(component, eOpts) {
        var loginWin = Ext.widget('loginWin');
        loginWin.show();
    }

});