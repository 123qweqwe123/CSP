import React from 'react'
import PropTypes from 'prop-types'
import { Modal, Form, Transfer, Button, Row, Col, Input, Table, DatePicker, AutoComplete, Tooltip, Select, message } from 'antd'
import moment from 'moment'
import lodash from 'lodash'
import styles from './Master.less'

const FormItem = Form.Item
const InputGroup = Input.Group
const Option = Select.Option

const Edit = ({
  form: {
    getFieldDecorator,
    getFieldsValue,
    getFieldValue,
    validateFields,
    setFieldsValue,
    resetFields
  },
}) => {
  const formItemLayout = {
    labelCol: {
      span: 4,
    },
    wrapperCol: {
      span: 20,
    }
  }

  const handleSubmit = () => {
    setFieldsValue({
      transNo: master.form.transNo
    })
    validateFields((errors) => {
      if (errors) {
        return
      }
      const data = {
        ...getFieldsValue(),
      }
      showPreviewModal(data)
    })
  }

  const handlerReset = () => {
    resetForm()
    resetFields()
  }

  const preview = (x, record) => {
    Modal.info({
      title: '冻存盒列表',
      content: (
        <Row>
          {
            master.boxes.boxGroupByLccCode[record.lccCode].boxes.map(x => {
              return <Col key={x} span={8}>{x}</Col>
            })
          }
        </Row>
      ),
      onOk() {
      }
    })
  }

  const columns = [
    { title: '序号', dataIndex: 'index' },
    // { title: '省份', dataIndex: 'rccName' },
    { title: '项目点', dataIndex: 'rccName' },
    {
      title: '项目单位',
      dataIndex: 'lccName'
    },
    { title: '取样套数', dataIndex: 'boxCount' },
    {
      title: '操作',
      dataIndex: 'operator',
      render: (text, record) => (
        <span>
          <a href="javascript:void(0)" onClick={x => preview(x, record)}>查看</a>
        </span>
      ),
    },
  ]


  const changeDate = (date, dateString) => {
    // let newTransNo = master.transNo.prefix + dateString + master.transNo.suffix
    // master.form.transNo = newTransNo
    // setFieldsValue({ transNo: newTransNo })

  }

  const selectUser = (createBy) => {
    let createByName = master.users.filter(x => {
      return x.value === createBy
    })[0].text
    setFieldsValue({ createByName })
  }

  const emailInfo = [
    {
      title: '新增转运邮箱', type: 1, field: 'transMail'
    },
    {
      title: '修改转运邮箱', type: 1, field: 'transMail'
    },
    {
      title: '新增项目邮箱', type: 2, field: 'projMail'
    },
    {
      title: '修改项目邮箱', type: 2, field: 'projMail'
    },
    {
      title: '新增样本邮箱', type: 3, field: 'spemMail'
    },
    {
      title: '修改样本邮箱', type: 3, field: 'spemMail'
    },
  ]

  const addOrUpdateEmail = (index) => {
    let item = emailInfo[index - 1]
    let value = getFieldValue(item.field)
    if (index % 2 === 0 && value.length !== 1) {
      message.error('请选中一条记录修改!')
      return
    }
    if (index % 2 === 0 && value.length === 1) {
      item.id = value[0]
    }
    showEmailModal(item)
  }

  const handlerChange = (targetKeys, direction, moveKeys) => {
    master.boxes.targetKeys = targetKeys
    // 将盒子按照 lccCode 分组
    let itemByLccCode = {}
    master.boxes.list.filter(x => {
      return lodash.includes(targetKeys, x.boxCode + '')  // 已选中的 box 对象
    }).forEach(x => {
      let lccCode = x.lccCode
      if (itemByLccCode[lccCode]) {
        itemByLccCode[lccCode].boxCount += 1
        itemByLccCode[lccCode].boxes.push(x.boxCode)
      } else {
        itemByLccCode[lccCode] = x
        itemByLccCode[lccCode].boxCount = 1
        itemByLccCode[lccCode].boxes = []
        itemByLccCode[lccCode].boxes.push(x.boxCode)
      }
    })
    let tableDatasource = []
    let boxesGroupByLccCode = ''
    let i = 1
    for (let lccCode in itemByLccCode) {
      let item = itemByLccCode[lccCode]
      item.index = i
      tableDatasource.push(item)
      boxesGroupByLccCode += itemByLccCode[lccCode].boxes.join(',')
      boxesGroupByLccCode += ';'
      i++
    }
    master.boxes.tableDataSource = tableDatasource
    master.boxes.boxesGroupByLccCodeStr = boxesGroupByLccCode
    master.boxes.boxGroupByLccCode = itemByLccCode
    // 如果包含未满的冻存盒，将标识是否确认选中
  }

  return (
    <Form layout="horizontal">
      <Row>
        <Col xs={24} md={12} className={styles.col}>
          <FormItem {...formItemLayout} label="转运编号">
            <Row>
              <Col span={16}>
                {getFieldDecorator('transNo', {
                  initialValue: master.form.transNo,
                })(
                  <Tooltip title={master.form.transNo}>
                    <span>
                      <InputGroup compact>
                        <Input style={{ width: '40%', color: '#108ee9', fontWeight: 'bold' }} value={master.transNo.prefix} disabled />
                        <DatePicker defaultValue={moment(master.transNo.dateStr, 'YYYYMMDD')} onChange={selectDate} disabled={master.transNo.disabled} format="YYYYMMDD"
                          style={{ width: '40%', fontColor: '#108ee9', fontWeight: 'bold' }} />
                        <Input style={{ width: '20%', color: '#108ee9', fontWeight: 'bold' }} value={master.transNo.suffix} disabled />
                      </InputGroup>
                    </span>
                  </Tooltip>
                  )}
              </Col>
              <Col span={8}>
                <Button type="primary" size="small" onClick={changeTransNoDisabled}>修改</Button>
              </Col>
            </Row>
          </FormItem>
          <FormItem {...formItemLayout} label="任务创建人">
            <Row>
              <Col span={16}>
                {getFieldDecorator('createBy', {
                  initialValue: master.form.createBy,
                  rules: [
                    {
                      required: true,
                      message: '任务创建人不能为空!',
                    },
                  ],
                })(
                  <AutoComplete
                    dataSource={master.users}
                    placeholder="输入姓名拼音首字母检索..."
                    onSelect={selectUser}
                    onSearch={searchUser}
                    disabled={master.createBy.disabled}
                  >
                  </AutoComplete>
                  )}
              </Col>
              <Col span={8}>
                <Button type="primary" size="small" onClick={changeCreateByDisabled}>修改</Button>
              </Col>
            </Row>
          </FormItem>
          <FormItem label="任务创建人" style={{ display: 'none' }}>
            {getFieldDecorator('createByName', {
              initialValue: master.form.createByName,
            })(
              <Input />
            )}
          </FormItem>
          <FormItem {...formItemLayout} label="取样项目点">
            <Row>
              <Col span={16}>
                {getFieldDecorator('rccCode', {
                  rules: [
                    {
                      required: true,
                      message: '取样项目点不能为空!',
                    },
                  ],
                })(
                  <AutoComplete
                    dataSource={master.rccs}
                    placeholder="输入项目点简拼首字母或者项目点编号检索..."
                    onSelect={selectRcc}
                    onSearch={searchRcc}
                  />
                )}
              </Col>
            </Row>
          </FormItem>
          <FormItem {...formItemLayout} label="转运邮箱">
            <Row>
              <Col span={16}>
                {getFieldDecorator('transMail', {
                  initialValue: master.transMails.filter((x) => {
                    return x.isDefault === 1
                  }).map((x) => {
                    return x.id
                  }),
                  rules: [
                    {
                      required: true,
                      message: '转运邮箱不能为空!',
                    },
                  ],
                })(
                  <Select
                    mode="multiple"
                    allowClear
                    style={{ width: '100%' }}
                  >
                    {master.transMails.map((x) => {
                      return <Option key={x.id}>{x.email}</Option>
                    })}
                  </Select>
                )}
              </Col>
              <Col span={8}>
                <Button type="primary"
                  size="small"
                  onClick={() => { addOrUpdateEmail(1) }}
                >
                  添加</Button>
                <Button type="primary"
                  size="small"
                  onClick={() => { addOrUpdateEmail(2) }}
                >
                  修改</Button>
              </Col>
            </Row>
          </FormItem>
          <FormItem {...formItemLayout} label="项目邮箱">
            <Row>
              <Col span={16}>
                {getFieldDecorator('projMail', {
                  initialValue: master.projMails.filter(x => {
                    return x.isDefault === 1
                  }).map(x => {
                    return x.id
                  }),
                  rules: [
                    {
                      required: true,
                      message: '项目邮箱不能为空!'
                    }
                  ],
                })(
                  <Select
                    mode="multiple"
                    allowClear
                    style={{ width: '100%' }}
                  >
                    {master.projMails.map(x => {
                      return <Option key={x.id}>{x.email}</Option>
                    })}
                  </Select>
                  )}
              </Col>
              <Col span={8}>
                <Button type="primary" size="small" onClick={x => {
                  addOrUpdateEmail(3)
                }}>添加</Button>
                <Button type="primary" size="small" onClick={x => {
                  addOrUpdateEmail(4)
                }}>修改</Button>
              </Col>
            </Row>
          </FormItem>
          <FormItem {...formItemLayout} label="样本邮箱">
            <Row>
              <Col span={16}>
                {getFieldDecorator('spemMail', {
                  initialValue: master.spemMails.filter(x => {
                    return x.isDefault === 1
                  }).map(x => {
                    return x.id
                  }),
                  rules: [
                    {
                      required: true,
                      message: '样本邮箱不能为空!'
                    }
                  ],
                })(
                  <Select
                    mode="multiple"
                    allowClear
                    style={{ width: '100%' }}
                  >
                    {master.spemMails.map(x => {
                      return <Option key={x.id}>{x.email}</Option>
                    })}
                  </Select>
                  )}
              </Col>
              <Col span={8}>
                <Button type="primary" size="small" onClick={x => {
                  addOrUpdateEmail(5)
                }}>添加</Button>
                <Button type="primary" size="small" onClick={x => {
                  addOrUpdateEmail(6)
                }}>修改</Button>
              </Col>
            </Row>
          </FormItem>
        </Col>
        <Col xs={24} md={12}>
          <FormItem {...formItemLayout} label="样本列表">
            {getFieldDecorator('boxes', {
              rules: [
                {
                  required: true,
                  message: '样本列表不能为空!',
                },
              ],
            })(
              <Transfer
                className={styles.transfer}
                showSearch
                dataSource={master.boxes.dataSource}
                targetKeys={master.boxes.targetKeys}
                notFoundContent="列表为空"
                searchPlaceholder="请输入搜索内容"
                titles={['未选中列表', '已选中列表']}
                onChange={(targetKeys, direction, moveKeys) => changeBoxes(targetKeys, direction, moveKeys)}
                render={item => item.title}
                listStyle={{
                  height: 300,
                }}
              />
            )}
          </FormItem>
        </Col>
      </Row>
      <Row>
        <Col xs={24} md={24}>
          <Table
            columns={columns}
            pagination={false}
            dataSource={master.boxes.tableDataSource}
            rowKey={record => record.index}
          />
        </Col>
      </Row>
      <Row style={{ marginTop: 20, textAlign: 'center' }}>
        <Button type="danger" onClick={handlerReset}>重置</Button>
        <Button type="primary" style={{ marginLeft: 10 }} onClick={handleSubmit}>预览</Button>
      </Row>
    </Form>
  )
}

Edit.propTypes = {
  onDeleteItem: PropTypes.func,
  onEditItem: PropTypes.func,
  isMotion: PropTypes.bool,
  location: PropTypes.object,
}

export default Form.create()(Edit)
