import React from 'react'
import PropTypes from 'prop-types'
import { api } from 'utils/config'
import { Form, Modal, Upload, Icon, Button, Input, AutoComplete, Alert } from 'antd'

const { common } = api
let { upload } = common
upload = BASE_PREFIX + upload

const FormItem = Form.Item

const formItemLayout = {
  labelCol: {
    span: 6,
  },
  wrapperCol: {
    span: 14,
  },
}

const modal = ({
  currentConfId,
  currentConfNo,
  handlerSearch,
  confNoDataSource,
  validatorErrMsg,
  fileList,
  uploadFile,
  form: {
    getFieldDecorator,
    getFieldsValue,
    validateFields,
    setFieldsValue,
  },
  onOk,
  ...modalProps
}) => {
  const handlerOk = () => {
    validateFields((errors) => {
      if (errors) {
        return
      }
      const data = {
        ...getFieldsValue(),
      }
      //  文件已上传到服务器，直接返回文件的 fileId
      if (data.file) {
        data.file = data.file.map(x => x.response.result).join(';')
      }
      onOk(data)
    })
  }

  const modalOpts = {
    ...modalProps,
    onOk: handlerOk,
  }

  const formatFile = (e) => {
    if (Array.isArray(e)) {
      return e
    }
    return e && e.fileList
  }

  // 清空
  const handlerChange = (val) => {
    if (val && val.length === 32) {
      setFieldsValue({ confId: val })
    } else {
      setFieldsValue({ confId: undefined })
    }
  }

  const checkConfNo = (rule, value, callback) => {
    const { confId } = getFieldsValue()
    if (!confId) {
      callback('请选择正确的会议编号')
    } else {
      callback()
    }
  }

  // 通过 fileList 限制只能上传一个文件
  const handlerFileChange = (info) => {
    let file = info.fileList
    file = file.slice(-1)
    uploadFile(file)
  }

  return (
    <Modal {...modalOpts}>
      <Form layout="horizontal">
        <FormItem label="会议编号" hasFeedback {...formItemLayout}>
          {getFieldDecorator('confNo', {
            initialValue: currentConfNo,
            rules: [{ required: true, message: '会议编号必须选择' },
              { validator: checkConfNo }],
          })(<AutoComplete
            style={{ width: '100%' }}
            dataSource={confNoDataSource}
            onSearch={handlerSearch}
            onChange={handlerChange}
            allowClear
          />)}
        </FormItem>
        <FormItem label="会议ID" hasFeedback {...formItemLayout} style={{ display: 'none' }}>
          {getFieldDecorator('confId', {
            initialValue: currentConfId,
          })(<Input />)}
        </FormItem>

        <FormItem label="选择文件" hasFeedback {...formItemLayout}>
          {getFieldDecorator('file', {
            valuePropName: 'result',
            getValueFromEvent: formatFile,
            rules: [
              {
                required: true,
                message: '上传文件不能为空!',
              },
            ],
          })(<Upload name="file"
            action={upload}
            fileList={fileList}
            onChange={handlerFileChange}
            accept=".xlsx"
          >
            <Button>
              <Icon type="upload" /> 点击上传
            </Button>
          </Upload>)}
        </FormItem>
        {validatorErrMsg &&
        <Alert
          message="校验错误"
          description={validatorErrMsg.split('|').map(x => <div>{x}</div>)}
          type="error"
          showIcon
        />
        }
      </Form>
    </Modal>
  )
}

modal.propTypes = {
  form: PropTypes.object.isRequired,
  type: PropTypes.string,
  item: PropTypes.object,
  onOk: PropTypes.func,
  getFieldsValue: PropTypes.func,
  validateFields: PropTypes.func,
  setFieldsValue: PropTypes.func,
  currentConfId: PropTypes.string,
  currentConfNo: PropTypes.string,
  handlerSearch: PropTypes.func,
  confNoDataSource: PropTypes.array,
  uploadFile: PropTypes.func,
  fileList: PropTypes.array,
  validatorErrMsg: PropTypes.any,
}

export default Form.create()(modal)
