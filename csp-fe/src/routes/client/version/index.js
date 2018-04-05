import React from 'react'
import PropTypes from 'prop-types'
import queryString from 'query-string'
import { Page } from 'components'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import List from './List'
import Filter from './Filter'

const Version = ({ location, dispatch, version, loading }) => {
  location.query = queryString.parse(location.search)
  const { list, pagination, currentItem, modalVisible, modalType, isMotion, permissionTreeData } = version
  const { pageSize } = pagination

  const listProps = {
    dataSource: list,
    loading: loading.effects['version/query'],
    pagination,
    location,
    isMotion,
    onChange(page) {
      const { query, pathname } = location
      dispatch(routerRedux.push({
        pathname,
        search: queryString.stringify({
          ...query,
          page: page.current,
          pageSize: page.pageSize,
        }),
      }))
    },

    updateDicVersion(record) {
      dispatch({
        type: 'version/update',
        payload: record,
      })
    },
  }

  const filterProps = {
    isMotion,
    filter: {
      ...location.query,
    },
    onFilterChange(value) {
      dispatch(routerRedux.push({
        pathname: location.pathname,
        search: queryString.stringify({
          ...value,
          page: 1,
          pageSize,
        }),
      }))
    },
  }

  return (
    <Page inner>
      <Filter {...filterProps} />
      <List {...listProps} />
      {modalVisible && <Modal {...modalProps} />}
    </Page>
  )
}

Version.propTypes = {
  user: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.object,
  version: PropTypes.any,
}

export default connect(({ version, loading }) => ({ version, loading }))(Version)
