import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { VendorSuchaz } from './vendor-suchaz.model';
import { VendorSuchazPopupService } from './vendor-suchaz-popup.service';
import { VendorSuchazService } from './vendor-suchaz.service';
import { CountrySuchaz, CountrySuchazService } from '../country-suchaz';

@Component({
    selector: 'jhi-vendor-suchaz-dialog',
    templateUrl: './vendor-suchaz-dialog.component.html'
})
export class VendorSuchazDialogComponent implements OnInit {

    vendor: VendorSuchaz;
    isSaving: boolean;

    countries: CountrySuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private vendorService: VendorSuchazService,
        private countryService: CountrySuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.countryService.query()
            .subscribe((res: HttpResponse<CountrySuchaz[]>) => { this.countries = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.vendor.id !== undefined) {
            this.subscribeToSaveResponse(
                this.vendorService.update(this.vendor));
        } else {
            this.subscribeToSaveResponse(
                this.vendorService.create(this.vendor));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<VendorSuchaz>>) {
        result.subscribe((res: HttpResponse<VendorSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: VendorSuchaz) {
        this.eventManager.broadcast({ name: 'vendorListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackCountryById(index: number, item: CountrySuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-vendor-suchaz-popup',
    template: ''
})
export class VendorSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private vendorPopupService: VendorSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.vendorPopupService
                    .open(VendorSuchazDialogComponent as Component, params['id']);
            } else {
                this.vendorPopupService
                    .open(VendorSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
