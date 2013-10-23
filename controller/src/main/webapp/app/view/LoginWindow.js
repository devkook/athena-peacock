/*
 * File: app/view/LoginWindow.js
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

Ext.define('Peacock.view.LoginWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.loginWin',

    height: 156,
    width: 346,
    resizable: false,
    layout: {
        type: 'fit'
    },
    closable: false,
    title: 'Login',
    modal: true,

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    id: 'loginForm',
                    bodyPadding: 10,
                    header: false,
                    title: 'My Form',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            id: 'lwLoginId',
                            fieldLabel: 'Username',
                            labelAlign: 'right',
                            name: 'login_id',
                            allowBlank: false
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Password',
                            labelAlign: 'right',
                            name: 'passwd',
                            inputType: 'password',
                            allowBlank: false
                        },
                        {
                            xtype: 'container',
                            layout: {
                                align: 'center',
                                padding: '10 0',
                                type: 'vbox'
                            },
                            items: [
                                {
                                    xtype: 'button',
                                    width: 50,
                                    text: 'login'
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