import axios from 'axios'

const host = 'http://192.168.31.218:8090/jeecg-boot'

axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'

export const PostLogin = (url, params) => {
  return axios({
    method: 'post',
    url: `${host}${url}`,
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export const getRequest = (url, params) => {
  return axios({
    method: 'get',
    url: `${host}${url}`,
    params: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export const postRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${host}${url}`,
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}
export const putRequest = (url, params) => {
  return axios({
    method: 'put',
    url: `${host}${url}`,
    data: params,
    transformRequest: [function (data) {
      let ret = ''
      for (const it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/json'
    }
  })
}
