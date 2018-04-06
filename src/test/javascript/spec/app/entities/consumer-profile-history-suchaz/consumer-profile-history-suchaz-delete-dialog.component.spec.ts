/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { ConsumerProfileHistorySuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/consumer-profile-history-suchaz/consumer-profile-history-suchaz-delete-dialog.component';
import { ConsumerProfileHistorySuchazService } from '../../../../../../main/webapp/app/entities/consumer-profile-history-suchaz/consumer-profile-history-suchaz.service';

describe('Component Tests', () => {

    describe('ConsumerProfileHistorySuchaz Management Delete Component', () => {
        let comp: ConsumerProfileHistorySuchazDeleteDialogComponent;
        let fixture: ComponentFixture<ConsumerProfileHistorySuchazDeleteDialogComponent>;
        let service: ConsumerProfileHistorySuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ConsumerProfileHistorySuchazDeleteDialogComponent],
                providers: [
                    ConsumerProfileHistorySuchazService
                ]
            })
            .overrideTemplate(ConsumerProfileHistorySuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConsumerProfileHistorySuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConsumerProfileHistorySuchazService);
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
