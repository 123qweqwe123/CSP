import React from 'react'
import PropTypes from 'prop-types'
import { Form, Input, Modal, Upload, Button, Icon, Select } from 'antd'
import { api } from 'utils/config'

const { common } = api
let { upload } = common
upload = BASE_PREFIX + upload

const FormItem = Form.Item
const { Option } = Select

const FormModal = ({
  personType,
  handlerSearch,
  onOk,
  identitys,
  item,
  identityList,
  layoutFileList,
  bottomFileList,
  uploadLayoutFile,
  uploadBottomFile,
  form: {
    getFieldDecorator,
    getFieldsValue,
    validateFields,
  },
  ...modalProps
}) => {
  const handleOk = () => {
    const data = {
      ...getFieldsValue(),
    }
    validateFields((errors) => {
      if (errors) {
        return
      }
      data.layoutfileId = data.file[0].response.result
      data.bottomfileId = data.file1[0].response.result
      data.confId = item.confId
      data.id = item.id
      onOk(data)
    })
  }
  const modalOpts = {
    ...modalProps,
    onOk: handleOk,
  }

  const formatFile = (e) => {
    if (Array.isArray(e)) {
      return e
    }
    return e && e.fileList
  }

  const formItemLayout = {
    labelCol: {
      xs: { span: 24 },
      sm: { span: 4 },
    },
    wrapperCol: {
      xs: { span: 24 },
      sm: { span: 20 },
    },
  }

  // 通过 fileList 限制只能上传一个文件
  const handlerFileChange = (info) => {
    let file = info.fileList
    file = file.slice(-1)
    uploadLayoutFile(file)
  }

  // 通过 fileList 限制只能上传一个文件
  const handlerFileChange1 = (info) => {
    let file = info.fileList
    file = file.slice(-1)
    uploadBottomFile(file)
  }

  const showFileList = (fileId) => {
    if (fileId) {
      return [{
        name: fileId,
        url: '',
        uid: fileId,
        status: 'done',
        response: {
          result: fileId,
        },
      }]
    }
    return undefined
  }

  // 如果列表已经包含了
  const validateIdentityType = (rule, value, callback) => {
    if (value && identityList.length > 0 && item.id == null) {
      for (let i in identityList) {
        if (`${identityList[i].identityType}` === value) {
          callback('该类型的模板已添加')
          return
        }
      }
      callback()
    } else {
      callback()
    }
  }

  return (
    <Modal {...modalOpts}>
      <Form layout="horizontal">
        <FormItem label="会议编号" hasFeedback {...formItemLayout}>
          {getFieldDecorator('confNo', {
            initialValue: item.confNo,
          })(<Input disabled />)}
        </FormItem>
        <FormItem label="人员类型" hasFeedback {...formItemLayout}>
          {getFieldDecorator('identityType', {
            initialValue: item.identityType !== undefined ? `${item.identityType}` : undefined,
            rules: [
              {
                required: true,
                message: '人员类型不能为空!',
              },
              {
                validator: validateIdentityType,
              },
            ],
          })(<Select disabled={item.id !== undefined}>
            {personType.map(x => <Option key={x.code}>{x.value}</Option>)}
          </Select>)}
        </FormItem>
        <FormItem label="布局文件" hasFeedback {...formItemLayout}>
          {getFieldDecorator('file', {
            valuePropName: 'result',
            getValueFromEvent: formatFile,
            initialValue: showFileList(item.layoutfileId),
            rules: [
              {
                required: true,
                message: '布局文件不能为空!',
              },
            ],
          })(<Upload name="file"
            action={upload}
            fileList={layoutFileList.length !== 0 ? layoutFileList : showFileList(item.layoutfileId)}
            onChange={handlerFileChange}
          >
            <Button>
              <Icon type="upload" /> 点击上传
            </Button>
          </Upload>)}
        </FormItem>
        <FormItem label="占位符" hasFeedback {...formItemLayout}>
          {getFieldDecorator('layoutAttrName', {
            initialValue: item.layoutAttrName,
          })(<Input />)}
        </FormItem>
        <FormItem label="查询语句" hasFeedback {...formItemLayout}>
          {getFieldDecorator('layoutQuerySql', {
            initialValue: item.layoutQuerySql,
          })(<Input />)}
        </FormItem>

        <FormItem label="查询参数" hasFeedback {...formItemLayout}>
          {getFieldDecorator('layoutQueryParameter', {
            initialValue: item.layoutQueryParameter,
          })(<Input />)}
        </FormItem>

        <FormItem label="底板文件" hasFeedback {...formItemLayout}>
          {getFieldDecorator('file1', {
            valuePropName: 'result',
            getValueFromEvent: formatFile,
            initialValue: showFileList(item.bottomfileId),
            rules: [
              {
                required: true,
                message: '上传底板文件不能为空!',
              },
            ],
          })(<Upload name="file"
            action={upload}
            fileList={bottomFileList.length !== 0 ? bottomFileList : showFileList(item.bottomfileId)}
            onChange={handlerFileChange1}
          >
            <Button>
              <Icon type="upload" /> 点击上传
            </Button>
          </Upload>)}
        </FormItem>
        <FormItem label="占位符" hasFeedback {...formItemLayout}>
          {getFieldDecorator('bottomAttrName', {
            initialValue: item.bottomAttrName,
          })(<Input />)}
        </FormItem>
        <FormItem label="查询语句" hasFeedback {...formItemLayout}>
          {getFieldDecorator('bottomQuerySql', {
            initialValue: item.bottomQuerySql,
          })(<Input />)}
        </FormItem>

        <FormItem label="查询参数" hasFeedback {...formItemLayout}>
          {getFieldDecorator('bottomQueryParameter', {
            initialValue: item.bottomQueryParameter,
          })(<Input />)}
        </FormItem>
      </Form>
    </Modal>
  )
}


FormModal.propTypes = {
  form: PropTypes.object.isRequired,
  type: PropTypes.string,
  onOk: PropTypes.func,
  identitys: PropTypes.object,
  item: PropTypes.object,
  personType: PropTypes.array,
  handlerSearch: PropTypes.func,
  getFieldsValue: PropTypes.func,
  layoutFileList: PropTypes.array,
  bottomFileList: PropTypes.array,
  uploadLayoutFile: PropTypes.func,
  uploadBottomFile: PropTypes.func,
}

export default Form.create()(FormModal)

