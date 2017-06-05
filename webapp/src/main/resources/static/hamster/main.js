class Main {
  init(combobox = 'cc', content = 'content', search = 'search') {
    $(`#${combobox}`).combobox({
      width: 100,
      data: [
        {
          text: '调戏机器人',
          value: '/chat/send*POST',
        },
        {
          text: '查询银行卡',
          value: '/bank/query*GET',
        },
        {
          text: '城市天气',
          value: '/map/weather/weatherInfo*GET'
        },
      ]
    });

    $(`#${content}`).panel({
      fit: true
    });
    $(`#${search}`).on('click', () => {
          const v = $(`#${combobox}`).combobox('getValue');
          if (v) {
            const keys = [];
            const values = [];
            $('input[name="key"]').each(function() {
              keys.push($(this).val());
            });
            $('input[name="value"]').each(function() {
              values.push($(this).val());
            });
            const url = v.substring(0, v.indexOf('*'));
            const method = v.substring(v.indexOf('*') + 1);
            let params = '';
            for (let i = 0; i < keys.length; ++i) {
              if (params.length) {
                params += '&' + keys[i] + '=' + values[i];
              } else {
                params = keys[i] + '=' + values[i];
              }
            }
            console.log(params);

            $.ajax({
              type: method,
              url,
              data: params,
              success: function (data) {
                console.log(JSON.stringify(data, null, 2));
                $(`#${content}`).panel({
                  content: `<pre><code>${JSON.stringify(data, null, 2)}</code></pre>`,
                });

                $('pre code').each(function(i, block) {
                  hljs.highlightBlock(block);
                });
              }
            });
          }
        }
    );
  }
};