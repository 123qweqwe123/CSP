import axios from 'axios'
import qs from 'qs'
import lodash from 'lodash'
import pathToRegexp from 'path-to-regexp'

const fetch = (options) => {
  let {
    method = 'get',
    data,
    fetchType,
    url,
  } = options

  const cloneData = lodash.cloneDeep(data)

  try {
    let domain = 'http://172.31.32.245:7000'
    // http://
    if (url.match(/[a-zA-z]+:\/\/[^/]*/)) {
      domain = url.match(/[a-zA-z]+:\/\/[^/]*/)[0]
      url = url.slice(domain.length)
    }
    const match = pathToRegexp.parse(url)
    // 如 url 为 /user/:id，解析为 /user/11
    url = pathToRegexp.compile(url)(data)
    // 解析 url 之后作用去掉 cloneData 里面的对应的解析参数，如上面的 id
    for (let item of match) {
      if (item instanceof Object && item.name in cloneData) {
        delete cloneData[item.name]
      }
    }
    url = domain + url
  } catch (e) {
    console.error(e.message)
  }
  const d = qs.stringify(cloneData, { indices: false })
  
  switch (method.toLowerCase()) {
    case 'get':
      return axios.get(url, {
        params: cloneData,
      })
    case 'delete':
      return axios.delete(url, {
        data: cloneData,
      })
    case 'post':
      return axios.post(url, qs.stringify(cloneData, { indices: false }))
    case 'put':
      return axios.put(url, qs.stringify(cloneData, { indices: false }))
    case 'patch':
      return axios.patch(url, qs.stringify(cloneData, { indices: false }))
    default:
      return axios(options)
  }
}

export default function request (options) {

  return fetch(options).then((response) => {
    const { statusText, status } = response
    let data = response.data || {}
    return {
      success: true,
      message: statusText,
      status,
      ...data,
    }
  }).catch((error) => {
    console.log(error)
    const { response } = error
    let msg
    let status
    let otherData = {}
    if (response) {
      const { data, statusText } = response
      otherData = data
      status = response.status
      msg = data.message || statusText
    } else {
      status = 600
      msg = 'Network Error'
    }
    // 如果没有认证，跳转到首页
    if (status === 401) {
    }
    return { success: false}
  })
}
