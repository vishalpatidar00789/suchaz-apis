import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { OfferSuchaz } from './offer-suchaz.model';
import { OfferSuchazPopupService } from './offer-suchaz-popup.service';
import { OfferSuchazService } from './offer-suchaz.service';

@Component({
    selector: 'jhi-offer-suchaz-delete-dialog',
    templateUrl: './offer-suchaz-delete-dialog.component.html'
})
export class OfferSuchazDeleteDialogComponent {

    offer: OfferSuchaz;

    constructor(
        private offerService: OfferSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.offerService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'offerListModification',
                content: 'Deleted an offer'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-offer-suchaz-delete-popup',
    template: ''
})
export class OfferSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private offerPopupService: OfferSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.offerPopupService
                .open(OfferSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
