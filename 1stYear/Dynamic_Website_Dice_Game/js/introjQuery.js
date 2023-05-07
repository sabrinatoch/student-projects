$(() => {

    $("#form").validate({
        rules: {
            firstName: {
                required: true,
                pattern: /(^[A-Z][A-Z\'\`\-\s]*?(?<!\`)$){1,20}/i
            },
            lastName: {
                required: true,
                pattern: /(^[A-Z][A-Z\'\`\-\s]*?(?<!\`)$){1,30}/i
            },
            user: {
                required: true,
                pattern: /^[A-Z][a-z]{3}[0-5]$/
            },
            email: {
                required: true,
                pattern: /^[A-Za-z0-9_\-.]*@[A-Za-z0-9_]*.(ca|org)$/,
            },
            phoneNumber: {
                required: true,
                pattern: /^\([0-9]{3}\)\s[0-9]{3}\-[0-9]{4}$/,
            },
            city: {
                required: true,
                pattern: /^[A-Za-z\s]{1,42}$/,
            },
            amt: {
                required: true,
                pattern: /^[0-9]*$/,
            },
        },
        messages: {
            firstName: {
                required: "⚠ Please enter your first name",
                pattern: "⚠ Invalid first name",
            },
            lastName: {
                required: "⚠ Please enter your last name",
                pattern: "⚠ Invalid last name",
            },
            user: {
                required: "⚠ Please enter your username",
                pattern: "⚠ Invalid username",
            },
            email: {
                required: "⚠ Please enter your email",
                pattern: "⚠ Invalid email",
            },
            phoneNumber: {
                required: "⚠ Please enter your phone #",
                pattern: "⚠ Invalid phone #",
            },
            city: {
                required: "⚠ Please enter your city",
                pattern: "⚠ Invalid city",
            },
            amt: {
                required: "⚠ Please enter your starting amount",
                pattern: "⚠ Invalid starting amount",
            },
        },
    })


});