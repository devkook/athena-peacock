/*
 * File: app/controller/MainToolbarController.js
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

Ext.define('Peacock.controller.MainToolbarController', {
    extend: 'Ext.app.Controller',

    onButtonClick: function(button, e, eOpts) {
        Peacock.app.debug("MainToolbarController.mainButton click. " + Peacock.app.menu_id);


        if (Peacock.app.menu_id == 'insts'){

            var win = Ext.widget('instanceLaunchWindow');
            win.show();

        }else if (Peacock.app.menu_id == 'rhevm-vms'){


        }else if (Peacock.app.menu_id == 'rhevm-tmps'){


        }else if (Peacock.app.menu_id == 'scal-grp'){


        }else if (Peacock.app.menu_id == 'scal-lb'){


        }else if (Peacock.app.menu_id == 'user-grp'){

            Ext.widget('userGroupFormWindow').show();

        }else if (Peacock.app.menu_id == 'users'){

            Ext.widget('userFormWindow').show();

        }
    },

    onMenuitemClick: function(item, e, eOpts) {

        Peacock.app.debug("MainToolbarController Action menu click. " + item.getId());



        if(item.getId() == "tbActionStart"){



        }else if(item.getId() == "tbActionStop"){



        }else if(item.getId() == "tbActionTerminate"){



        }else if(item.getId() == "tbActionEdit"){

            this.onActionEditClick();


        }else if(item.getId() == "tbActionDelete"){

            this.onActionDeleteClick();


        }else if(item.getId() == "tbActionRegister"){


        }else if(item.getId() == "tbActionSWInstall"){
            Ext.widget('swInstallLaunchWindow').show();

        }
    },

    onTextfieldKeydown: function(textfield, e, eOpts) {
        if(e.getKey() == e.ENTER){

            var mainGridStore = Ext.getCmp("mainGridPanel").getStore();

            mainGridStore.getProxy().setExtraParam( "search", textfield.getRawValue() );

            mainGridStore.load();
        }
    },

    onActionEditClick: function() {

        if (Peacock.app.menu_id == 'insts'){


        }else if (Peacock.app.menu_id == 'rhevm-vms'){


        }else if (Peacock.app.menu_id == 'rhevm-tmps'){


        }else if (Peacock.app.menu_id == 'scal-grp'){


        }else if (Peacock.app.menu_id == 'scal-lb'){


        }else if (Peacock.app.menu_id == 'user-grp'){


            Ext.widget('userGroupFormWindow').show();

            var formPanel = Ext.getCmp("userGroupForm");

            formPanel.up('window').setTitle("Edit Group");

            formPanel.getForm().load({
                params : {
                    group_id : Peacock.app.selectedRecord.get("group_id")
                },
                url : "usergroup/getUserGroup",
                waitMsg: 'Loading...'
            });


        }else if (Peacock.app.menu_id == 'users'){

            Ext.widget('userFormWindow').show();

            var formPanel = Ext.getCmp("userForm");

            formPanel.up('window').setTitle("Edit User");

            formPanel.getForm().load({
                params : {
                    user_id : Peacock.app.selectedRecord.get("user_id")
                },
                url : "user/getUser",
                waitMsg: 'Loading...'
            });


        }
    },

    onActionDeleteClick: function() {

        var _pararms = {};
        var _url = "";

        if (Peacock.app.menu_id == 'insts'){


        }else if (Peacock.app.menu_id == 'rhevm-vms'){


        }else if (Peacock.app.menu_id == 'rhevm-tmps'){


        }else if (Peacock.app.menu_id == 'scal-grp'){


        }else if (Peacock.app.menu_id == 'scal-lb'){


        }else if (Peacock.app.menu_id == 'user-grp'){

            _url = "usergroup/delete";
            _pararms = {group_id : Peacock.app.selectedRecord.get("group_id")};

        }else if (Peacock.app.menu_id == 'users'){

            _url = "user/delete";
            _pararms = {user_id : Peacock.app.selectedRecord.get("user_id")};

        }



        Ext.MessageBox.confirm('Confirm', '삭제 하시겠습니까?', function(btn){

            if(btn == "yes"){

                Ext.Ajax.request({
                    url: _url,
                    params: _pararms,
                    waitMsg: 'Delete Data...',
                    success: function(response){

                        Ext.getCmp('mainGridPanel').getStore().reload();

                        Ext.getCmp('detailPanel').removeAll();
                    }
                });
            }

        });
    },

    init: function(application) {
        this.control({
            "#mainToolbar #mainButton": {
                click: this.onButtonClick
            },
            "#mainToolbar menuitem": {
                click: this.onMenuitemClick
            },
            "#mainToolbar textfield": {
                keydown: this.onTextfieldKeydown
            }
        });
    }

});
