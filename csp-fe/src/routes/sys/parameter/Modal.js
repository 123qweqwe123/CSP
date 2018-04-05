import React from 'react'
import PropTypes from 'prop-types'
import { Form, Input, Modal, Switch } from 'antd'

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
  onOk,
  roles,
  loginUser,
  item = {},
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
      const data = {
        ...getFieldsValue(),
      }
      if (data.isValid) {
        data.isValid = 1
      } else {
        data.isValid = 0
      }
      onOk(data)
    })
  }

  const modalOpts = {
    ...modalProps,
    onOk: handleOk,
  }

  return (
    <Modal {...modalOpts}>
      <Form layout="horizontal">
        <FormItem label="参数类型名称" hasFeedback {...formItemLayout}>
          {getFieldDecorator('value', {
            initialValue: item.value,
            rules: [
              {
                required: true,
                message: '参数类型名称不能为空!',
                whitespace: true,
              },
            ],
          })(<Input />)}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="是否有效"
        >
          {getFieldDecorator('isValid', { valuePropName: 'checked', initialValue: item.isValid !== 0 })(
            <Switch checkedChildren="是" unCheckedChildren="否" />
          )}
        </FormItem>
        <FormItem label="备注" hasFeedback {...formItemLayout}>
          {getFieldDecorator('remark', {
            initialValue: item.remark,
          })(<Input disabled={!!item.reamrk} />)}
        </FormItem>
      </Form>
    </Modal>
  )
}

modal.propTypes = {
  form: PropTypes.object.isRequired,
  type: PropTypes.string,
  item: PropTypes.object,
  onOk: PropTypes.func,
  roles: PropTypes.array,
  loginUser: PropTypes.object,
}

export default Form.create()(modal)
