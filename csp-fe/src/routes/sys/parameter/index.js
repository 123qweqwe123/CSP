import React from 'react'
import PropTypes from 'prop-types'
import queryString from 'query-string'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Page } from 'components'
import List from './List'
import Filter from './Filter'
import Modal from './Modal'
import ParameterModal from './ParameterModal'

const Parameter = ({
  location, dispatch, parameter, loading,
}) => {
  const {
    list, pagination, currentItem, modalVisible, modalType, isMotion,
    currentSubItem, subList, parameterModalVisible, parameterModalType, nameDataSource,
  } = parameter
  const { pageSize } = pagination

  const modalProps = {
    item: modalType === 'create' ? {} : currentItem,
    visible: modalVisible,
    maskClosable: false,
    confirmLoading: loading.effects[`${modalType === 'create' ? 'parameter/create' : 'parameter/update'}`],
    title: `${modalType === 'create' ? '添加参数类型' : '修改参数类型'}`,
    wrapClassName: 'vertical-center-modal',
    onOk (data) {
      dispatch({
        type: `parameter/${modalType}`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'parameter/hideModal',
      })
    },
  }

  const parameterModalProps = {
    item: currentSubItem,
    visible: parameterModalVisible,
    maskClosable: false,
    confirmLoading: loading.effects[`${parameterModalType === 'create' ? 'parameter/createParameter' : 'parameter/updateParameter'}`],
    title: `${parameterModalType === 'create' ? '添加参数' : '修改参数'}`,
    wrapClassName: 'vertical-center-modal',
    onOk (data) {
      dispatch({
        type: `parameter/${parameterModalType}Parameter`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'parameter/hideParameterModal',
      })
    },
  }

  const listProps = {
    pagination,
    location,
    isMotion,
    subList,
    dataSource: list,
    loading: loading.effects['parameter/query'],
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
    onDeleteItem (id) {
      dispatch({
        type: 'parameter/delete',
        payload: id,
      })
    },
    onEditItem (item) {
      dispatch({
        type: 'parameter/showModal',
        payload: {
          modalType: 'update',
          currentItem: item,
        },
      })
    },
    onEditSubItem (item) {
      dispatch({
        type: 'parameter/showParameterModal',
        payload: {
          parameterModalType: 'update',
          currentSubItem: item,
        },
      })
    },
    onDeleteSubItem (id) {
      dispatch({
        type: 'parameter/deleteParameter',
        payload: id,
      })
    },
    onExpand (isExpand, record) {
      if (isExpand) {
        dispatch({
          type: 'parameter/queryParameter',
          payload: { typeCode: record.code },
        })
      }
    },
    handlerUp (record, subRecord) {
      dispatch({
        type: 'parameter/sortParameter',
        payload: { record, subRecord, operator: 'up' },
      })
    },
    handlerDown (record, subRecord) {
      dispatch({
        type: 'parameter/sortParameter',
        payload: { record, subRecord, operator: 'down' },
      })
    },
    onAddParameter (record) {
      dispatch({
        type: 'parameter/showParameterModal',
        payload: { record },
      })
    },
  }

  const filterProps = {
    isMotion,
    nameDataSource,
    filter: {
      ...location.query,
    },
    onFilterChange (value) {
      dispatch(routerRedux.push({
        pathname: location.pathname,
        search: queryString.stringify({
          ...value,
          page: 1,
          pageSize,
        }),
      }))
    },
    onSearch (fieldsValue) {
      fieldsValue.keyword.length ? dispatch(routerRedux.push({
        pathname: '/user',
        query: {
          field: fieldsValue.field,
          keyword: fieldsValue.keyword,
        },
      })) : dispatch(routerRedux.push({
        pathname: '/user',
      }))
    },
    onAdd () {
      dispatch({
        type: 'parameter/showModal',
        payload: {
          modalType: 'create',
        },
      })
    },
    changeNameDataSource (val) {
      dispatch({
        type: 'parameter/change',
        payload: {
          q: val, // 查询字符串
          s: 'ac6', // 查询标识
        },
      })
    },

  }

  return (
    <Page inner>
      <Filter {...filterProps} />
      <List {...listProps} />
      {modalVisible && <Modal {...modalProps} />}
      {parameterModalVisible && <ParameterModal {...parameterModalProps} />}
    </Page>
  )
}

Parameter.propTypes = {
  user: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.object,
  parameter: PropTypes.object,
}

export default connect(({ parameter, loading }) => ({ parameter, loading }))(Parameter)
