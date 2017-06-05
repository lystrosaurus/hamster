class Menu {
  static init(mainDiv = 'main', menuUl = 'menu') {
    const setting = {
      callback: {
        onClick: zTreeOnclick,
      }
    };

    function zTreeOnclick(event, treeId, treeNode) {
      if (treeNode.id === 10) {
        const center = $(`#${mainDiv}`).layout('panel', 'center');
        center.panel('setTitle', '主窗口');
        center.panel('refresh', '/main');
      }
    };
    const zNodes = [
      {
        id: 0,
        name: "hamster",
        open: true,
        iconOpen: "/ztree/css/zTreeStyle/img/diy/1_open.png",
        iconClose: "/ztree/css/zTreeStyle/img/diy/1_close.png",
        children: [
          {
            id: 10,
            name: "hamster",
            icon: '/ztree/css/zTreeStyle/img/diy/5.png',
          },
        ]
      }
    ];

    return $.fn.zTree.init($("#menu"), setting, zNodes);
  }
}
;