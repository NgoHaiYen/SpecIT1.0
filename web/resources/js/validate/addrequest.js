function Requests(){
    var self = this;
    self.name = ko.observable();
    self.deadline = ko.observable();
    self.content = ko.observable();
    self.uploadImage = ko.observable();

    self.buttonEnabled = ko.computed(function() {
        var image = self.uploadImage();
        if (image == null) return false;
        if (!image.endsWith(".png") && !image.endsWith(".jpeg")) return false;
        return (self.name() && self.deadline() && self.content());
    });
}
ko.applyBindings(new Requests());
