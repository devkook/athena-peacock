/*
 * File: app.js
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

//@require @packageOverrides
Ext.Loader.setConfig({
    enabled: true
});

Ext.application({
    models: [
        'UsersModel',
        'MachineModel',
        'SoftwareModel',
        'OSPackageModel',
        'MyModel',
        'UserGroupMapModel',
        'ASGroupModel'
    ],
    stores: [
        'SoftwareListStore',
        'OSPackageListStore',
        'UserGroupMapStore',
        'UserGroupListStore',
        'ASGroupListStore'
    ],
    views: [
        'MainViewport',
        'LoginWindow',
        'InstanceLaunchWindow',
        'UserGroupFormWindow',
        'UserFormWindow'
    ],
    autoCreateViewport: true,
    controllers: [
        'LoginController',
        'MenuController',
        'MainGridController',
        'InstanceTabPanelController',
        'UsersTabPanelController',
        'UserGroupTabPanelController',
        'MainToolbarController',
        'GlobalAjaxController'
    ],
    name: 'Peacock',

    debug: function(log) {
        var debugTextArea = Ext.getCmp("debugTextArea");

        debugTextArea.setRawValue(log + "\r\n" +debugTextArea.getRawValue());
    }

});
