import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { VendorSuchaz } from './vendor-suchaz.model';
import { VendorSuchazPopupService } from './vendor-suchaz-popup.service';
import { VendorSuchazService } from './vendor-suchaz.service';

@Component({
    selector: 'jhi-vendor-suchaz-delete-dialog',
    templateUrl: './vendor-suchaz-delete-dialog.component.html'
})
export class VendorSuchazDeleteDialogComponent {

    vendor: VendorSuchaz;

    constructor(
        private vendorService: VendorSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vendorService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'vendorListModification',
                content: 'Deleted an vendor'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-vendor-suchaz-delete-popup',
    template: ''
})
export class VendorSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private vendorPopupService: VendorSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.vendorPopupService
                .open(VendorSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
