module.exports = {
  name: '会议服务平台',
  version: '0.0.1',
  prefix: 'csp',
  footerText: 'Copyright © 2017. 量码博信 . All rights reserved',
  logo: '/logo.svg',
  qrCode: '/QR_code.jpg',
  iconFontCSS: '/iconfont.css',
  iconFontJS: '/iconfont.js',
  CORS: [],
  openPages: ['/login'],
  api: {
    userLogin: '/user/login',
    userLogout: '/user/logout',
    userInfo: '/userInfo',
    users: '/users',
    user: '/user/:id',
    dashboard: '/dashboards',
    conferences: '/conferences',
    common: {
      autoComplete: '/common/combox/autoComplete',
      select: '/common/combox/select',
      upload: '/common/file/upload',
      preview: '/common/file/preview',
      download: '/common/file/download',
      downloadTemplate: '/common/file/template',
    },
    sys: {
      accounts: '/sys/accounts',
      menus: '/sys/menus',
      permissions: '/sys/permissions',
      roles: '/sys/roles',
      loginLog: '/sys/loginLogs',
      operateLog: '/operation/logs',
      parameters: '/sys/parameters',
      exlinks: '/sys/exlinks',
    },
    dataversion: '/dataversion',
    dataversionlogs: '/dataversion/versionLogs',
    currentVersionLogs: '/dataversion/currentVersionLogs',
  },
  dict: {
    personType: 'T001', // 人员类型
    linkType: 'T002', // 报表类型
    idType: 'T003', // 证件类型
    checkinType: 'T004', // 签到类型
  },
  combox: { // 自动补全下拉框
    confNoCombo: 'ac1',
    placeCombo: 'ac2', // 会议会场
  },
}
