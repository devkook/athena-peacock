/*
 * File: app/view/VMTabPanel.js
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

Ext.define('Peacock.view.VMTabPanel', {
    extend: 'Ext.tab.Panel',
    alias: 'widget.vmTabPanel',

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
                    title: 'General',
                    items: [
                        {
                            xtype: 'form',
                            id: 'vmDescForm',
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
                                            fieldLabel: 'Name',
                                            name: 'vmId'
                                        },
                                        {
                                            xtype: 'textfield',
                                            fieldLabel: 'Display Type',
                                            name: 'display'
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
                                            fieldLabel: 'Description',
                                            name: 'description'
                                        },
                                        {
                                            xtype: 'textfield',
                                            fieldLabel: 'Origin',
                                            name: 'origin'
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
                                            fieldLabel: 'Template',
                                            name: 'template'
                                        },
                                        {
                                            xtype: 'textfield',
                                            fieldLabel: 'Priority',
                                            name: 'priority'
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
                                            fieldLabel: 'Defined Memory',
                                            name: 'memory'
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
                                            fieldLabel: 'OS Name',
                                            name: 'os'
                                        },
                                        {
                                            xtype: 'textfield',
                                            fieldLabel: 'CPU Cores',
                                            name: 'cores'
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    title: 'Network Interfaces',
                    items: [
                        {
                            xtype: 'gridpanel',
                            id: 'vmNicGrid',
                            header: false,
                            title: 'My Grid Panel',
                            store: 'NicListJsonStore',
                            columns: [
                                {
                                    xtype: 'rownumberer'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 138,
                                    dataIndex: 'name',
                                    text: 'Name'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 151,
                                    dataIndex: 'networkName',
                                    text: 'Network Name'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 125,
                                    dataIndex: 'type',
                                    text: 'Type'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 200,
                                    defaultWidth: 200,
                                    dataIndex: 'macAddress',
                                    text: 'MAC Address'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 200,
                                    defaultWidth: 200,
                                    dataIndex: 'speed',
                                    text: 'Speed(Mbps)'
                                }
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

});