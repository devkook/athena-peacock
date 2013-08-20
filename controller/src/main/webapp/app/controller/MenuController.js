/*
 * File: app/controller/MenuController.js
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

Ext.define('Peacock.controller.MenuController', {
    extend: 'Ext.app.Controller',

    stores: [
        'UsersJsonStore',
        'MachineListStore'
    ],

    onTreepanelItemClick: function(dataview, record, item, index, e, eOpts) {

        Peacock.app.debug("MenuController.onTreepanelItemClick. " + record.get('id'));

        Peacock.app.menu_id = record.get('id');//선택한 메뉴 id 저장.



        Ext.getCmp("tbActionMenu").setDisabled(true);


        if(record.get('id') == 'inst-dash'){

            Ext.getCmp('centerContainer').layout.setActiveItem(0);

        }else {

            Ext.getCmp('centerContainer').layout.setActiveItem(1);

            var grid = Ext.getCmp('mainGridPanel');

            var pagingToolbar = grid.getDockedItems('toolbar[dock="bottom"]')[0];

            Ext.suspendLayouts();


            if (record.get('id') == 'insts'){
                //alert("instances.");


                this.viewInstances(grid);

            }else if (record.get('id') == 'img-tmp'){
                alert("templates.");

            }else if (record.get('id') == 'img-pkg'){
                alert("packages.");

            }else if (record.get('id') == 'scal-grp'){
                alert("scal-grp.");

            }else if (record.get('id') == 'scal-lb'){
                alert("scal-lb.");

            }else if (record.get('id') == 'user-grp'){
                alert("user-grp.");

            }else if (record.get('id') == 'users'){


                this.viewUsers(grid);
            }


            pagingToolbar.bindStore(grid.getStore());


            Ext.resumeLayouts(true);

            grid.getStore().load();
        }
    },

    viewUsers: function(grid) {
        /*
        *  user list 화면 보여주기.
        */


        grid.setTitle('Users');
        grid.reconfigure(Ext.getStore('UsersJsonStore'), [{
            text: 'ID',
            dataIndex: 'id'
        }, {
            text: 'User Name',
            dataIndex: 'name'
        }, {
            text: 'Groups',
            dataIndex: 'groups'
        }, {
            text: 'Password',
            dataIndex: 'password'
        }, {
            text: 'Create Date',
            dataIndex: 'createDate',
            width: 200
        }]);


        /*
        * toolbar menu 활성화
        */
        Ext.getCmp("mainButton").setText("Create New User");

        Ext.getCmp("tbActionStart").hide();
        Ext.getCmp("tbActionStop").hide();
        Ext.getCmp("tbActionTerminate").hide();
        Ext.getCmp("tbActionEdit").show();
        Ext.getCmp("tbActionDelete").show();
        Ext.getCmp("tbActionRegister").hide();

    },

    viewInstances: function(grid) {
        /*
        *  instance list 화면 보여주기.
        */


        grid.setTitle('Instance List');
        grid.reconfigure(Ext.getStore('MachineListStore'), [{
            text: 'ID',
            dataIndex: 'machine_id'
        }, {
            text: 'OS Name',
            dataIndex: 'os_name',
            width: 200
        }, {
            text: 'Template',
            dataIndex: 'template',
            width: 200
        }, {
            text: 'Staus',
            dataIndex: 'status'
        }, {
            text: 'Uptime',
            dataIndex: 'uptime'

        }, {
            text: 'Host',
            dataIndex: 'host_name',
            width: 200
        }]);


        /*
        * toolbar menu 활성화
        */
        Ext.getCmp("mainButton").setText("Launch");

        Ext.getCmp("tbActionStart").show();
        Ext.getCmp("tbActionStop").show();
        Ext.getCmp("tbActionTerminate").show();
        Ext.getCmp("tbActionEdit").hide();
        Ext.getCmp("tbActionDelete").hide();
        Ext.getCmp("tbActionRegister").hide();


    },

    init: function(application) {
        this.control({
            "#westPanel treepanel": {
                itemclick: this.onTreepanelItemClick
            }
        });
    }

});
