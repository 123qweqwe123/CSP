import React from 'react'
import PropTypes from 'prop-types'
import { connect } from 'dva'
import { Switch, Icon, Card } from 'antd'
import { Page } from 'components'
import { MyReactGridLayout, Browser } from './conponents'
import styles from './index.less'

/**
 * 所有用户都有初始的仪表盘
 * 未选择仪表盘 = 系统仪表盘 - 用户仪表盘
 * 已选择仪表盘默认为系统仪表盘，如果用户做了编辑操作，则保存起来
 * @param {*} param0
 */
function Dashboard ({ dashboard, dispatch }) {
  const { enableSetting, checkedDashboards, uncheckedDashboards,
    browser } = dashboard
  const changeLayout = (currLayout) => {
    dispatch({ type: 'dashboard/changeLayout', payload: { layout: currLayout } })
    dispatch({ type: 'dashboard/saveLayout', payload: { currLayout } })
  }
  const onChange = (checked) => {
    dispatch({ type: 'dashboard/changeSetting', payload: { enableSetting: checked } })
  }

  /**
   * 获取面板
   * @param {*} index 面板的 i 属性
   * @param {*} height 动态计算面板的可用高度
   */
  const getComponentWithHeight = (index, height) => {
    console.log(index, height)
    switch (index) {
      case '1':
        return <Browser data={browser} height={height} />
      case '2':
        return <div>正在开发...</div>
      default:
        return null
    }
  }

  const closeDashboard = (i) => {
    dispatch({ type: 'dashboard/closeDashboard', payload: { key: i } })
  }

  const openDashboard = (i) => {
    dispatch({ type: 'dashboard/openDashboard', payload: { key: i } })
  }

  const generateUserDom = () => {
    return checkedDashboards.map((d) => {
      const height = (d.h * 150) - 32
      return (
        <div key={d.i} data-grid={{ ...d }}>
          <Card title={d.title} style={{ height: '100%' }}>
            {getComponentWithHeight(d.i, height)}
            {enableSetting &&
              <Icon
                type="close"
                onClick={() => closeDashboard(d.i)}
                style={{
                  position: 'absolute',
                  right: 5,
                  top: 5,
                  cursor: 'pointer',
                }}
              />
            }
          </Card>
        </div>
      )
    })
  }

  // 生成未选择的  dom
  const generateUncheckedDom = () => {
    return uncheckedDashboards.map((d) => {
      const height = (d.h * 150) - 32
      return (
        <div key={d.i} data-grid={{ ...d }}>
          <Card title={d.title} style={{ height: '100%' }}>
            {getComponentWithHeight(d.i, height)}
            <Icon
              type="close"
              onClick={() => openDashboard(d.i)}
              style={{
                position: 'absolute',
                right: 5,
                top: 5,
                cursor: 'pointer',
              }}
            />
          </Card>
        </div>
      )
    })
  }

  return (
    <Page className={styles.dashboard}>
      <Switch
        checked={enableSetting}
        checkedChildren={<Icon type="edit" />}
        onChange={onChange}
        unCheckedChildren={<Icon type="setting" />}
        style={{
          position: 'absolute',
          right: 0,
          top: -28,
        }}
      />
      {enableSetting && <div style={{ fontWeight: 'bold', color: '#0f8ee9' }}>已选择面板-{checkedDashboards.length}</div>}
      <MyReactGridLayout
        className="layout"
        onLayoutChange={changeLayout}
        isDraggable={enableSetting}
        layout={checkedDashboards}
        isResizable={enableSetting}
        width={1200}
      >
        {generateUserDom()}
      </MyReactGridLayout>
      {enableSetting &&
        <div>
          <hr style={{ border: '2px solid #f8f8f8' }} />
          <div style={{ fontWeight: 'bold', color: '#0f8ee9' }}>未选择面板-{uncheckedDashboards.length}</div>
          {uncheckedDashboards.length > 0 &&
            <MyReactGridLayout
              className="layout"
              isDraggable={false}
              layout={uncheckedDashboards}
              isResizable={false}
              width={1200}
            >
              {generateUncheckedDom()}
            </MyReactGridLayout>
          }
        </div>
      }
    </Page>
  )
}

Dashboard.propTypes = {
  dashboard: PropTypes.object,
  dispatch: PropTypes.func,
}

export default connect(({ dashboard }) => ({ dashboard }))(Dashboard)
