import React from 'react'
import PropTypes from 'prop-types'
import queryString from 'query-string'
import { connect } from 'dva'
import { routerRedux } from 'dva/router'
import { Page } from 'components'
import List from './List'
import Modal from './Modal'


const Exlink = ({ location, dispatch, exlink, loading }) => {
  const { list, pagination, currentItem, modalVisible, isMotion, linkType, modalType } = exlink

  const listProps = {
    dataSource: list,
    loading: loading.effects['exlink/query'],
    pagination,
    location,
    isMotion,
    onAdd () {
      dispatch({
        type: 'exlink/showModal',
        payload: {
          record: {},
        },
      })
    },
    onChange (page) {
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
    updateReport (record) {
      dispatch({
        type: 'exlink/showModal',
        payload: {
          record,
        },
      })
    },
    deleteReport (id) {
      dispatch({
        type: 'exlink/delete',
        payload: {
          id,
        },
      })
    },
  }

  const modalProps = {
    linkType,
    modalItem: modalType === 'create' ? {} : currentItem,
    visible: modalVisible,
    confirmLoading: loading.effects[`${modalType === 'create' ? 'exlink/create' : 'exlink/update'}`],
    onCancel () {
      dispatch({
        type: 'exlink/hideModal',
      })
    },
    onOk (data) {
      dispatch({
        type: `exlink/${modalType}`,
        payload: {
          data,
        },
      })
    },
  }

  return (
    <Page inner>
      <List {...listProps} />
      {modalVisible && <Modal {...modalProps} />}
    </Page>
  )
}

Exlink.propTypes = {
  exlink: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.object,
  onChange: PropTypes.func,
}

export default connect(({ exlink, loading }) => ({ exlink, loading }))(Exlink)
