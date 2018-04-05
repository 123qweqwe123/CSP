import React from 'react'
import PropTypes from 'prop-types'
import { Route, Switch, Redirect, routerRedux } from 'dva/router'
import { LocaleProvider } from 'antd'
import zh_CN from 'antd/lib/locale-provider/zh_CN'
import 'moment/locale/zh-cn'
import dynamic from 'dva/dynamic'
import App from 'routes/app'

const { ConnectedRouter } = routerRedux

const Routers = function ({ history, app }) {
  const error = dynamic({
    app,
    component: () => import('./routes/error'),
  })
  const routes = [
    {
      path: '/dashboard',
      models: () => [import('./models/dashboard')],
      component: () => import('./routes/dashboard/'),
    },
    {
      path: '/sys/account',
      models: () => [import('./models/sys/account')],
      component: () => import('./routes/sys/account/'),
    },
    {
      path: '/sys/menu',
      models: () => [import('./models/sys/menu')],
      component: () => import('./routes/sys/menu/'),
    },
    {
      path: '/sys/permission',
      models: () => [import('./models/sys/permission')],
      component: () => import('./routes/sys/permission/'),
    },
    {
      path: '/sys/role',
      models: () => [import('./models/sys/role')],
      component: () => import('./routes/sys/role/'),
    },
    {
      path: '/sys/parameter',
      models: () => [import('./models/sys/parameter')],
      component: () => import('./routes/sys/parameter/'),
    },
    {
      path: '/sys/loginlog',
      models: () => [import('./models/sys/loginLog')],
      component: () => import('./routes/sys/loginLog/'),
    },
    {
      path: '/sys/exlink',
      models: () => [import('./models/sys/exlink')],
      component: () => import('./routes/sys/exlink/'),
    },
    {
      path: '/exlink/:exlinkId',
      models: () => [import('./models/sys/exlink_detail')],
      component: () => import('./routes/sys/exlink/detail/'),
    },
    {
      path: '/login',
      models: () => [import('./models/login')],
      component: () => import('./routes/login/'),
    },
    {
      path: '/conference',
      models: () => [import('./models/conference')],
      component: () => import('./routes/conference/'),
    },
    {
      path: '/client/versionmanage',
      models: () => [import('./models/version')],
      component: () => import('./routes/client/version/'),
    },
    {
      path: '/client/pagemanage',
      models: () => [import('./models/pageversion')],
      component: () => import('./routes/client/pageversion/'),
    },
    {
      path: '/chart/rabbitMQCount',
      models: () => [import('./models/chart/rabbitMQCount')],
      component: () => import('./routes/chart/rabbitMQCount/'),
    },
  ]

  return (
    <ConnectedRouter history={history}>
      <LocaleProvider locale={zh_CN}>
        <App>
          <Switch>
            <Route exact path="/" render={() => (<Redirect to="/dashboard" />)} />
            {
              routes.map(({ path, ...dynamics }, key) => (
                <Route key={key} exact path={path} component={dynamic({ app, ...dynamics })} />
              ))
            }
            <Route component={error} />
          </Switch>
        </App>
      </LocaleProvider>
    </ConnectedRouter>
  )
}

Routers.propTypes = {
  history: PropTypes.object,
  app: PropTypes.object,
}

export default Routers
