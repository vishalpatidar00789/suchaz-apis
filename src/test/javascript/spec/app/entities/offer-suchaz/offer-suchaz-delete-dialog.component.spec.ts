/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { OfferSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/offer-suchaz/offer-suchaz-delete-dialog.component';
import { OfferSuchazService } from '../../../../../../main/webapp/app/entities/offer-suchaz/offer-suchaz.service';

describe('Component Tests', () => {

    describe('OfferSuchaz Management Delete Component', () => {
        let comp: OfferSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<OfferSuchazDeleteDialogComponent>;
        let service: OfferSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [OfferSuchazDeleteDialogComponent],
                providers: [
                    OfferSuchazService
                ]
            })
            .overrideTemplate(OfferSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OfferSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OfferSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
