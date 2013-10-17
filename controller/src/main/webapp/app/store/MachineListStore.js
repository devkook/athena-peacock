/*
 * File: app/store/MachineListStore.js
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

Ext.define('Peacock.store.MachineListStore', {
    extend: 'Ext.data.Store',

    requires: [
        'Peacock.model.MachineModel'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            model: 'Peacock.model.MachineModel',
            storeId: 'MachineListStore',
            pageSize: 10,
            proxy: me.processMyAjaxProxy1({
                type: 'ajax',
                url: 'static/machineList.json',
                reader: {
                    type: 'json',
                    root: 'list'
                }
            }),
            listeners: {
                beforeload: {
                    fn: me.onJsonstoreBeforeLoad,
                    scope: me
                }
            }
        }, cfg)]);
    },

    processMyAjaxProxy1: function(config) {
        config.actionMethods = {create: "POST", read: "POST", update: "POST", destroy: "POST"};

        return config;
    },

    onJsonstoreBeforeLoad: function(store, operation, eOpts) {

        Peacock.app.debug("MachineListStore.onBeforeLoad.");

        Ext.getCmp('detailPanel').removeAll(true);
    }

});