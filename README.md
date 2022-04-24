> a android system Info theaf client app, and a server for saving 

client target:
- bluetooth info
    - bluetooth name of current device
    - list of bluetooth info of paired devices, including:
        - bluetooth name
        - bluetooth device address
- clipboard content
- list of files under Downloads folder
- list of installed apps
- location information
    - longtitude
    - latitude
- system information
    - device name
    - android version
    - phone brand
    - phone manufacture
    - android Release version
    - SDK version
    - host name
    - cpu core number
    - ram info
        - total ram size
        - availiable ram size
    - rom info
        - total rom size
        - availiable rom size
    - screen size

after get all these information, this app will send information to server in JSON .

server target:
- receive JSON data and save it to mongodb
- display JSON data
- delete all JSON data
