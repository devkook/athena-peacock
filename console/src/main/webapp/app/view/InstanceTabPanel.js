/*
 * File: app/view/InstanceTabPanel.js
 *
 * This file was generated by Sencha Architect version 2.2.2.
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
    width: 755,
    activeTab: 0,

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
                        type: 'column'
                    },
                    title: 'Description',
                    items: [
                        {
                            xtype: 'propertygrid',
                            columnWidth: 0.5,
                            padding: 5,
                            header: false,
                            title: 'My Property Grid',
                            enableColumnHide: false,
                            enableColumnResize: false,
                            source: {
                                'Property 1': 'String',
                                'Property 2': true,
                                'Property 3': '2013-08-08T13:41:39',
                                'Property 4': 123
                            }
                        },
                        {
                            xtype: 'propertygrid',
                            columnWidth: 0.5,
                            padding: '5 5 5 0',
                            header: false,
                            title: 'My Property Grid',
                            source: {
                                'Property 1': 'String',
                                'Property 2': true,
                                'Property 3': '2013-08-08T13:41:48',
                                'Property 4': 123
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    title: 'Software'
                },
                {
                    xtype: 'panel',
                    title: 'Tab 3'
                }
            ]
        });

        me.callParent(arguments);
    }

});