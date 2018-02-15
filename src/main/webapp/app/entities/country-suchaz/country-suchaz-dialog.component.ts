import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CountrySuchaz } from './country-suchaz.model';
import { CountrySuchazPopupService } from './country-suchaz-popup.service';
import { CountrySuchazService } from './country-suchaz.service';

@Component({
    selector: 'jhi-country-suchaz-dialog',
    templateUrl: './country-suchaz-dialog.component.html'
})
export class CountrySuchazDialogComponent implements OnInit {

    country: CountrySuchaz;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private countryService: CountrySuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.country.id !== undefined) {
            this.subscribeToSaveResponse(
                this.countryService.update(this.country));
        } else {
            this.subscribeToSaveResponse(
                this.countryService.create(this.country));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CountrySuchaz>>) {
        result.subscribe((res: HttpResponse<CountrySuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CountrySuchaz) {
        this.eventManager.broadcast({ name: 'countryListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-country-suchaz-popup',
    template: ''
})
export class CountrySuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private countryPopupService: CountrySuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.countryPopupService
                    .open(CountrySuchazDialogComponent as Component, params['id']);
            } else {
                this.countryPopupService
                    .open(CountrySuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
