import React from 'react'
import PropTypes from 'prop-types'
import { Form, Upload, Modal, Icon, message, Input } from 'antd'
import { api } from '../../../utils/config'

const FormItem = Form.Item
const Dragger = Upload.Dragger
const { common } = api
let { upload } = common
upload = BASE_PREFIX + upload

const modal = ({
  isDisable,
  onAble,
  onOk,
  onDisable,
  changeUpload,
  form: {
    getFieldDecorator,
    getFieldsValue,
    //validateFields,
  },
  ...modalProps
}) => {
  const handleOk = () => {
    const data = {
      ...getFieldsValue(),
    }
  if(data.file){
    onOk(data)
  }else{
  message.error('请选择需要上传的文件')
  }

    // validateFields((errors) => {
     // if (errors) {
      //  return
     // }
    //  onOk(data)
  //  })
  }

  const modalOpts = {
    title: '版本更新',
    closable: false,
    maskClosable: false,
    ...modalProps,
    onOk: handleOk,
  }

  const props = {
    name: 'file',
    multiple: false,
    showUploadList: true,
    disabled: isDisable,
    action: upload,
    onChange(info) {
      const status = info.file.status
      if (status === 'removed') {
        if (typeof info.file.error === 'undefined') {
          onAble()
        }
        return
      }
      onDisable()
      if (status !== 'uploading') {
        // console.log(info.file, info.fileList)
      }
      if (status === 'done') {
        message.success(`${info.file.name} 文件上传成功.`)
        const payload = { isUpload: true }
        changeUpload(payload)
      } else if (status === 'error') {
        message.error(`${info.file.name} 文件上传失败.`)
        onAble()
      }
    },
  }

  return (
    <Modal {...modalOpts}>
      <Form>
        {getFieldDecorator('file', {
          rules: [
            {
              required: true,
              message: '上传文件不能为空!',
            },
          ],
        })(
          <Dragger {...props}>
            <p className="ant-upload-drag-icon">
              <Icon type="inbox" />
            </p>
            <p className="ant-upload-text">请选择需要上传的文件</p>
          </Dragger>
          )}

        <FormItem label="版本信息" style={{ marginTop: 1 }}>
          {getFieldDecorator('remark', {
            rules: [
              {
                required: true,
                message: '版本信息不能为空!',
              },
            ],
          })(<Input type="textarea" rows={3} />)}
        </FormItem>
        <FormItem label="版本号" style={{ marginTop: 1 }}>
          {getFieldDecorator('versionName', {
            rules: [
              {
                required: true,
                message: '版本号不能为空!',
              },
            ],
          })(<Input type="textarea" rows={3} />)}
        </FormItem>
        <FormItem label="版本代码" style={{ marginTop: 1 }}>
          {getFieldDecorator('versionCode', {
            rules: [
              {
                required: true,
                pattern: /^[0-9]*$/,
                message: '版本代码不能为空且必须为数字!',
              },
            ],
          })(<Input type="textarea" rows={3} />)}
        </FormItem>
      </Form>
    </Modal>
  )
}

modal.propTypes = {
  form: PropTypes.object.isRequired,
  isDisable: PropTypes.bool,
  onAble: PropTypes.func,
  onOk: PropTypes.func,
  onDisable: PropTypes.func,
  changeUpload: PropTypes.func,
}

export default Form.create()(modal)
