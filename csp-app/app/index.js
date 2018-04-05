import React from 'react'
import { AppRegistry } from 'react-native'

import dva from './utils/dva'
import Router from './router'

import appModel from './models/app'
import routerModel from './models/router'
import homeModel from './models/home'

const app = dva({
  initialState: {},
  models: [appModel, routerModel, homeModel],
  onError(e) {
    console.log('onError', e)
  },
})

const CspApp = app.start(<Router />)

export default CspApp

AppRegistry.registerComponent('CspApp', () => CspApp)

