import React from 'react'
import PropTypes from 'prop-types'
import { Form, Input, Modal, Select } from 'antd'

const FormItem = Form.Item
const Option = Select.Option

const formItemLayout = {
  labelCol: {
    span: 6,
  },
  wrapperCol: {
    span: 14,
  },
}

const modal = ({
  modalItem,
  linkType,
  onOk,
  onCancel,
  form: {
    getFieldDecorator,
    validateFields,
    getFieldsValue,
  },
  ...modalProps
}) => {
  const handleOk = () => {
    validateFields((errors) => {
      if (errors) {
        return
      }
      /* 判断是否修改过内容，没有修改则不提交 */
      const fields = getFieldsValue()
      if (fields.url === modalItem.url && fields.height === modalItem.height &&
        fields.width === modalItem.width && fields.display === modalItem.display &&
        fields.position === modalItem.position && fields.remark === modalItem.remark &&
        fields.allowfullscreen === modalItem.allowfullscreen
      ) {
        onCancel()
      } else {
        const data = {
          id: modalItem.id || '',
          ...getFieldsValue(),
        }
        onOk(data)
      }
    })
  }

  const modalOpts = {
    ...modalProps,
    title: modalItem.id ? '修改报表' : '新增报表',
    closable: false,
    maskClosable: false,
    onOk: handleOk,
    onCancel,
  }

  return (
    <Modal layout="horizontal" {...modalOpts}>
      <FormItem label="URL" {...formItemLayout}>
        {getFieldDecorator('url', {
          initialValue: modalItem.url,
          rules: [
            {
              required: true,
              message: 'URL不能为空!',
              whitespace: true,
            },
          ],
        })(<Input />)}
      </FormItem>

      <FormItem label="高度" {...formItemLayout}>
        {getFieldDecorator('height', {
          initialValue: modalItem.height,
          rules: [
            {
              required: true,
              message: '高度不能为空!',
              whitespace: true,
            },
          ],
        })(<Input />)}
      </FormItem>

      <FormItem label="类型" {...formItemLayout}>
        {getFieldDecorator('linkType', {
          initialValue: modalItem.linkType,
        })(<Select style={{ width: '100%' }}>
          {linkType.map(t => <Option key={t.code}>{t.value}</Option>)}
        </Select>)}
      </FormItem>

      <FormItem label="宽度" {...formItemLayout}>
        {getFieldDecorator('width', {
          initialValue: modalItem.width || '',
        })(<Input />)}
      </FormItem>

      <FormItem label="显示" {...formItemLayout}>
        {getFieldDecorator('display', {
          initialValue: modalItem.display || 'initial',
        })(<Input />)}
      </FormItem>

      <FormItem label="位置" {...formItemLayout}>
        {getFieldDecorator('position', {
          initialValue: modalItem.position || 'relative',
        })(
          <Select style={{ width: '100%' }} allowClear>
            <Option key="static">static</Option>
            <Option key="relative">relative</Option>
            <Option key="absolute">absolute</Option>
            <Option key="fixed">fixed</Option>
          </Select>
        )}
      </FormItem>

      <FormItem label="全屏" {...formItemLayout}>
        {getFieldDecorator('allowfullscreen', {
          initialValue: modalItem.allowfullscreen ? modalItem.allowfullscreen.toString() : '1',
        })(
          <Select style={{ width: '100%' }} allowClear>
            <Option key="1">是</Option>
            <Option key="0">否</Option>
          </Select>
        )}
      </FormItem>

      <FormItem label="说明" {...formItemLayout}>
        {getFieldDecorator('remark', {
          initialValue: modalItem.remark || '',
        })(<Input type="textarea" rows={3} />)}
      </FormItem>
    </Modal>
  )
}

modal.propTypes = {
  form: PropTypes.object.isRequired,
  modalItem: PropTypes.object,
  onOk: PropTypes.func,
  onCancel: PropTypes.func,
  linkType: PropTypes.array,
}

export default Form.create()(modal)
