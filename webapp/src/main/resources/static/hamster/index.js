class Index {
  static init(div = 'main') {
    return $(`#${div}`).layout({
      'fit': true
    }).layout('add', {
      region: 'west',
      width: 180,
      title: '菜单',
      split: true,
      href: '/menu',
    }).layout('add', {
      region: 'east',
      width: 180,
      title: '属性',
      split: true,
    }).layout('add', {
      region: 'north',
      title: 'Hamster',
      split: true,
    }).layout('add', {
      region: 'south',
      title: 'copyright',
      split: true,
    }).layout('add', {
      region: 'center',
      title: 'center',
      split: true,
    })
  }
}
;