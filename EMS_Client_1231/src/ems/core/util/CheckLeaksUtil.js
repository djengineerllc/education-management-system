//用于解决内存问题，检查Ext状态的几个方法:
//统计新增组件
function countAddComs() {
    var items = Ext.ComponentMgr.all.items;
    var comIds = window.extComIds = window.extComIds || {};
    for (var i = 0; i < items.length; i++)
    {
        comIds[items[i].id] = (comIds[items[i].id] || 0) + 1;
    }
    var result = [];
    var adds = [];
    for (var key in comIds)
    {
        if (comIds[key] >= 1)
        {
            result.push(key + "=" + comIds[key]);
            if (comIds[key] == 1) {
                //显示新增的组件
                //console.log(Ext.getCmp(key)) //firefox+firebug下可用这句查看组件详细的信息
                adds.push(Ext.getCmp(key).ctype + " " + Ext.getCmp(key).getXType() + " " + Ext.getCmp(key).text)
            }
        }
    }
    alert(adds.join(","));
    alert(result.join(", "));
}






//统计各全局组件管理器数量
function count() {
    var result = [];
    var count = 0;
    var x = Ext.menu.MenuMgr.menus;
    if (x) {
        for (var key in x)
        {
            count++;
        }
        result.push("menus = " + count);
    }

    count = 0;
    x = Ext.dd.ScrollManager.els;
    if (x) {
        for (var key in x)
        {
            count++;
        }
        result.push("scrolls = " + count);
    }
    count = 0;
    x = Ext.dd.DragDropMgr.ids;
    if (x) {
        var keys = "";
        for (var key in x)
        {
            count++;
        }
        result.push("dragdrop = " + count + " " + keys);

    }
    x = Ext.ButtonToggleMgr.groups;
    if (x) {
        for (var key in x)
        {
            count++;
        }
        result.push("butontoggle = " + count);
    }
    result.push("comp = " + Ext.ComponentMgr.all.length)
    result.push("store = " + Ext.StoreMgr.length)
    if (Ext.util.TaskRunner.tasks)
    {
        result.push("tasks = " + Ext.util.TaskRunner.tasks.length)
    }
    alert(result.join("\n"));
}






//统计Elements和事件数量
function countElmentsAndEvents() {
    //统计
    var elementCacheCouunt = 0;
    for (var key in Ext.Element.cache)
    {
        elementCacheCouunt++;
    }
    var _flyweightsCount = 0;
    for (var key in Ext.Element._flyweights)
    {
        _flyweightsCount++;
    }
    var eventCount = 0;
    var enames = {};
    for (var key in Ext.EventManager.elHash)
    {
        eventCount++;
        var es = Ext.EventManager.elHash[key]
        if (es) {
            for (var ename in es) {
                if (es.hasOwnProperty(ename)) {
                    enames[ename] = (enames[ename] || 0) + 1
                }
            }
        }
    }
    //显示各个全局变量的数量
    alert("elementCacheCouunt = " + elementCacheCouunt + ", _flyweightsCount = " + _flyweightsCount + ",eventCount = " + eventCount)
    //比较事件的增长
    var result = [];
    window.extEventNames = window.extEventNames || {};
    for (var key in enames)
    {
        window.extEventNames[key] = window.extEventNames[key] || 0;
        var added = enames[key] - window.extEventNames[key];
        if (added > 0)
        {
            result.push(key + ":total = " + enames[key] + ",add =" + added);
        }
    }
                //显示增长的事件及数量
    alert(result.join(","))
                //记录本次统计的事件名，留做与新的事件做比较
    window.extEventNames = enames;
}