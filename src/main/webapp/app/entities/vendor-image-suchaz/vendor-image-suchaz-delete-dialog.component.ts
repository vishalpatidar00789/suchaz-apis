import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { VendorImageSuchaz } from './vendor-image-suchaz.model';
import { VendorImageSuchazPopupService } from './vendor-image-suchaz-popup.service';
import { VendorImageSuchazService } from './vendor-image-suchaz.service';

@Component({
    selector: 'jhi-vendor-image-suchaz-delete-dialog',
    templateUrl: './vendor-image-suchaz-delete-dialog.component.html'
})
export class VendorImageSuchazDeleteDialogComponent {

    vendorImage: VendorImageSuchaz;

    constructor(
        private vendorImageService: VendorImageSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vendorImageService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'vendorImageListModification',
                content: 'Deleted an vendorImage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-vendor-image-suchaz-delete-popup',
    template: ''
})
export class VendorImageSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private vendorImagePopupService: VendorImageSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.vendorImagePopupService
                .open(VendorImageSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
