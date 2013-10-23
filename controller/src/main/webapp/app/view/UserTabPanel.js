/*
 * File: app/view/UserTabPanel.js
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

Ext.define('Peacock.view.UserTabPanel', {
    extend: 'Ext.tab.Panel',
    alias: 'widget.userTabPanel',

    height: 325,
    width: 755,
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
                    title: 'Groups',
                    items: [
                        {
                            xtype: 'gridpanel',
                            id: 'userGroupsGrid',
                            header: false,
                            title: 'My Grid Panel',
                            store: 'UserGroupMapStore',
                            columns: [
                                {
                                    xtype: 'rownumberer'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 200,
                                    dataIndex: 'group_name',
                                    text: 'Group'
                                },
                                {
                                    xtype: 'actioncolumn',
                                    align: 'center',
                                    menuText: 'Actions',
                                    items: [
                                        {
                                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                                alert("Delete "+ record.get("group_name"));
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
                    autoScroll: true,
                    title: 'Summary',
                    items: [
                        {
                            xtype: 'propertygrid',
                            id: 'userSummaryGrid',
                            header: false,
                            title: 'My Property Grid',
                            sortableColumns: false,
                            source: {
                                status: 'Loading ...'
                            }
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

});