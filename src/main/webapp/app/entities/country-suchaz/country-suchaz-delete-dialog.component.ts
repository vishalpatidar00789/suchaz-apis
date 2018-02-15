import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CountrySuchaz } from './country-suchaz.model';
import { CountrySuchazPopupService } from './country-suchaz-popup.service';
import { CountrySuchazService } from './country-suchaz.service';

@Component({
    selector: 'jhi-country-suchaz-delete-dialog',
    templateUrl: './country-suchaz-delete-dialog.component.html'
})
export class CountrySuchazDeleteDialogComponent {

    country: CountrySuchaz;

    constructor(
        private countryService: CountrySuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.countryService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'countryListModification',
                content: 'Deleted an country'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-country-suchaz-delete-popup',
    template: ''
})
export class CountrySuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private countryPopupService: CountrySuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.countryPopupService
                .open(CountrySuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
